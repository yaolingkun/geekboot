package com.springbootplus.core.logger;

public class LoggerFactory {

	private static Logger logger = null;
	private static Object lock = new Object();
	public static Logger getLogger(){
		if(null == logger){
			synchronized (lock) {
				if(null == logger){
					logger = new Logger();
//					logger.init(out_put_level, out2where, log_dir);
				}
			}
		}
		
		return logger;
	}
}
