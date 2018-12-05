package com.bloom.domain.petal;
/**
 * Petal HTML页面渲染
 * @author xuliangliang
 *
 */
public class PetalPageRender {
	/**
	 * 
	 * @param html
	 * @return
	 */
	public static String render(String html) {
		return PETAL_PAGE_HTMEL_TEMPLATE_PREFIX.concat(html).concat(PETAL_PAGE_HTMEL_TEMPLATE_SUFFIX);
	}
	
	private static final String PETAL_PAGE_HTMEL_TEMPLATE_PREFIX = 
			"<!Doctype html>\r\n" + 
			"      <html>\r\n" + 
			"        <head>\r\n" + 
			"		   <meta charset=\"UTF-8\"></meta>" +
			"          <title></title>\r\n" + 
			"          <style>\r\n" + 
			"            html,body{\r\n" + 
			"              height: 100%;\r\n" + 
			"              margin: 0;\r\n" + 
			"              padding: 0;\r\n" + 
			"              overflow: auto;\r\n" + 
			"              background-color: #f1f2f3;\r\n" + 
			"            }\r\n" + 
			"            .container{\r\n" + 
			"              box-sizing: border-box;\r\n" + 
			"              width: 1000px;\r\n" + 
			"              max-width: 100%;\r\n" + 
			"              min-height: 100%;\r\n" + 
			"              margin: 0 auto;\r\n" + 
			"              padding: 30px 20px;\r\n" + 
			"              overflow: hidden;\r\n" + 
			"              background-color: #fff;\r\n" + 
			"              border-right: solid 1px #eee;\r\n" + 
			"              border-left: solid 1px #eee;\r\n" + 
			"            }\r\n" + 
			"            .container img,\r\n" + 
			"            .container audio,\r\n" + 
			"            .container video{\r\n" + 
			"              max-width: 100%;\r\n" + 
			"              height: auto;\r\n" + 
			"            }\r\n" + 
			"            .container p{\r\n" + 
			"              white-space: pre-wrap;\r\n" + 
			"              min-height: 1em;\r\n" + 
			"            }\r\n" + 
			"            .container pre{\r\n" + 
			"              padding: 15px;\r\n" + 
			"              background-color: #f1f1f1;\r\n" + 
			"              border-radius: 5px;\r\n" + 
			"            }\r\n" + 
			"            .container blockquote{\r\n" + 
			"              margin: 0;\r\n" + 
			"              padding: 15px;\r\n" + 
			"              background-color: #f1f1f1;\r\n" + 
			"              border-left: 3px solid #d1d1d1;\r\n" + 
			"            }\r\n" + 
			"          </style>\r\n" + 
			"        </head>\r\n" + 
			"        <body>\r\n" + 
			"          <div class=\"container\">";
	private static final String PETAL_PAGE_HTMEL_TEMPLATE_SUFFIX = "</div></body></html>";
}
