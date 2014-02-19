package com.edao.codes.solr;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.SolrRequest.METHOD;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.PivotField;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.apache.solr.common.util.NamedList;

import com.edao.codes.beans.GroupResult;
import com.edao.codes.beans.GroupResultComparator;

/**
 * @author liushuai
 *
 */
public class SolrJClient {
	
	SolrServer solrServer = null;
	
	public SolrJClient(String url) {
		try {
			solrServer = new HttpSolrServer(url);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	private QueryResponse query(SolrQuery query) throws SolrServerException {
		QueryResponse rsp = null;
		try {
			rsp = solrServer.query(query, METHOD.POST); //查询
		} catch (SolrServerException e) {
			throw e;
		}
		
		return rsp;
	}
	
	/**
	 * facet.pivot查询，即支点查询，类似group by
	 * @param pivotQuery
	 */
	public SearchResult pivotQuery(PivotQuery pivotQuery) {
		SolrQuery query = new SolrQuery();
		query.setQuery(pivotQuery.getQuery());
		query.setStart(0);
		query.setRows(0);
		query.setFacet(true);
		//query.setParam("facet.query", "last_modified:[2013-02-31T13:52:39Z TO *]");
		query.setParam("facet.pivot", pivotQuery.getPivots());
		
		SolrDocumentList docs = null;
		QueryResponse rsp = null;
		try {
			rsp = query(query);
		} catch (SolrServerException e) {
			e.printStackTrace();
		}
		
		String[] fields = pivotQuery.getPivots().split(",");
		List<GroupResult> results = new ArrayList<GroupResult>();
		int groupCount = 0;
		if (rsp != null) {
			docs = rsp.getResults();
			NamedList<List<PivotField>> facetPivot = rsp.getFacetPivot();
			Iterator<Entry<String, List<PivotField>>> it = facetPivot.iterator();
			while (it.hasNext()) {
				Entry<String, List<PivotField>> entry = it.next();
				System.out.println("key : " + entry.getKey());
				System.out.println("value: " + entry.getValue());
				List<PivotField> pfList = entry.getValue();
				
				for (PivotField pf : pfList) {
					traversal(pf, pf.getValue(), results);
					//traversal1(pf, pf.getValue());
					//traversal2(pf, null);
				}
			}
			
			Collections.sort(results, new GroupResultComparator(ORDER.desc));
			System.out.println("queryTime : " + rsp.getQTime());
		}
		
		SearchResult result = new SearchResult();
		result.setItems(results);
		result.setTotalCount(results.size());
		
		return result;
	}
	
	boolean isAuditLevel(String str) {
		return str.matches("(high|middle|low)");
	}
	
	private void traversal2(PivotField pf, PivotField ppf) {
		String field = pf.getField();
		Object val = pf.getValue();
		int count = pf.getCount();
		List<PivotField> list = pf.getPivot();
		if (list != null && list.size() > 0){
			for (int i=0; i<list.size(); i++) {
				traversal2(list.get(i), pf);
			}
		} else {
			//if (field.equals("host") && val.equals("6_115")) {
			System.out.println(count + ", " + val + ", " + field);
			//}
		}
		
	}
	
	private void traversal1(PivotField pf, Object group) {
		String field = pf.getField();
		Object val = pf.getValue();
		int count = pf.getCount();
		List<PivotField> list = pf.getPivot();
		if (list != null && list.size() > 0){
			for (int i=0; i<list.size(); i++) {
				traversal1(list.get(i), list.get(i).getValue());
			}
		} else {
			if (field.equals("host") && val.equals("6_115")) {
			System.out.println(count + ", " + val + ", " + field);
			}
		}
		
	}
	
	private void traversal(PivotField pf, Object group, List<GroupResult> results) {
		List<PivotField> list = pf.getPivot();
		
		String field = pf.getField();
		if (list!=null && list.size() > 0) {
			for (PivotField pff : list) {
				traversal(pff, group + "," + pff.getValue(), results);
			}
		} else {
			results.add(new GroupResult(""+group.toString().toUpperCase(), pf.getCount()));
		}
		
	}
	
	/**
	 * add collectiion of SolrInputDocuments
	 * @param c
	 */
	public void add(Collection<SolrInputDocument> docs) {
		try {
			solrServer.add(docs);
			solrServer.commit();
		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * commit
	 */
	public void commit() {
		try {
			solrServer.commit();
		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
