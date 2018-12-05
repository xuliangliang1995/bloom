package com.bloom.util.image;
/**
 * 随机产生一个图片链接
 * @author xuliangliang
 *
 */
public class RandomImage {
	private static String [] storage = {
			"https://grasswort.oss-cn-hangzhou.aliyuncs.com/logo/grasswort.png"
			/*"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1536778670264&di=07c7563309915c3a239345a3820d6f3b&imgtype=0&src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F01cc13554242c80000019ae9e173e9.jpg%401280w_1l_2o_100sh.jpg",
			"http://i2.hdslb.com/bfs/archive/d39d2b33387a77f23e5e405156f329fde53ca142.jpg",
			"http://pic1.win4000.com/wallpaper/2018-08-24/5b7fa5a5c0104.jpg"*/
	};
	
	public static String get() {
		return storage[(int)(Math.random()*storage.length)];
	}

}
