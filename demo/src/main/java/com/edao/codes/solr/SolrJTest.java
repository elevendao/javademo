package com.edao.codes.solr;

import java.util.ArrayList;
import java.util.List;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.common.SolrInputDocument;

public class SolrJTest {
	

	public static void main(String[] args) throws Exception {
		new SolrJTest().testaddd();
	}
	
	public void testaddd() {
		HttpSolrServer server = new HttpSolrServer("http://172.16.4.121:8983/solr/access_201404");
		String start = "15900021381";
		for (int i=0; i<5; i++) {
			new Thread(new Executor(server, start+i, i)).start();
		}
	}
	
	class Executor implements Runnable {
		SolrServer solrServer = null;
		String start = "";
		int num;
		public Executor(HttpSolrServer solrServer, String start, int num) {
			this.start = start;
			this.solrServer = solrServer;
			this.num = num;
		}
		public void run() {
				long d1 = System.currentTimeMillis();
				try {
					List<SolrInputDocument> docs = new ArrayList<SolrInputDocument>();
					for (int i=0; i<500; i++) {
						SolrInputDocument doc = new SolrInputDocument();
					    doc.addField("id",this.start + i);
					    docs.add(doc);
					}
					solrServer.add(docs);
					solrServer.commit();
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				long d2 = System.currentTimeMillis();
				System.out.println("thread1 time taken : " + (d2 - d1) + "ms");
		}
	}

}
