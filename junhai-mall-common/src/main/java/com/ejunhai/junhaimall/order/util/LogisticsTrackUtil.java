package com.ejunhai.junhaimall.order.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import org.apache.log4j.Logger;

import com.ejunhai.junhaimall.order.model.OrderLogisticsTrack;

/**
 * 订单物流跟踪查询
 * 
 * @author wuyang
 * 
 */
public class LogisticsTrackUtil {

	private final static Logger logger = Logger.getLogger(LogisticsTrackUtil.class);

	/**
	 * 获取跟踪对象
	 * 
	 * @param expressOrderId
	 *            快递单号
	 * @return OrderLogisticsTrack
	 */
	public static OrderLogisticsTrack getLogisticsTrack(String expressOrderId) {
		OrderLogisticsTrack orderLogisticsTrack = null;
		String errMsg = "";
		String content = "";
		InputStream urlStream = null;
		try {
			URL url = new URL(
					"http://api.ickd.cn/?com=shunfeng&type=json&encode=utf-8&ord=desc&id=F88275FED9B2AFD04ECF53BD4EEFB3F9&nu="
							+ expressOrderId);
			URLConnection con = url.openConnection();
			con.setAllowUserInteraction(false);
			con.setConnectTimeout(30000);// 连接超时
			con.setReadTimeout(30000);// 读操作超时
			urlStream = url.openStream();
			String type = con.guessContentTypeFromStream(urlStream);
			String charSet = null;
			if (type == null) {
				type = con.getContentType();
			}

			byte b[] = new byte[10000];
			int numRead = urlStream.read(b);
			content = new String(b, 0, numRead);
			while (numRead != -1) {
				numRead = urlStream.read(b);
				if (numRead != -1) {
					String newContent = new String(b, 0, numRead, charSet);
					content += newContent;
				}
			}

		} catch (Exception e) {
			logger.error("快递API查询时发生异常", e);
			errMsg = "快递API查询时发生异常：" + e.getMessage();
		} finally {
			if (urlStream != null) {
				try {
					urlStream.close();
				} catch (IOException e) {
					logger.error("urlStream.close()异常", e);
				}
			}
		}

//		if (StringUtils.isEmpty(errMsg) && StringUtils.isNotEmpty(content)) {
//			JSONObject jsonObj = JSONObject.fromObject(content);
//			orderLogisticsTrack = (OrderLogisticsTrack) JSONObject.toBean(jsonObj, OrderLogisticsTrack.class);
//		}
		return orderLogisticsTrack;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		LogisticsTrackUtil LogisticsTrackUtil = new LogisticsTrackUtil();
		System.out.println(LogisticsTrackUtil.getLogisticsTrack("594099159820"));
	}
}
