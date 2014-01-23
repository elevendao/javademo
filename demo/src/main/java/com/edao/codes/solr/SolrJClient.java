package com.edao.codes.solr;

import java.io.IOException;
import java.util.Collection;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.common.SolrInputDocument;

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
	
	public void add(Collection<SolrInputDocument> c) {
		try {
			solrServer.add(c);
		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
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
