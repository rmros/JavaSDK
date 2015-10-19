package io.cloudboost;
import junit.framework.Assert;
import org.junit.Test;

public class CloudNotificationTest{
	
	void initialize(){
		CloudApp.init("sample123","9SPxp6D3OPWvxj0asw5ryA==");
	}
	
	@Test(timeout = 20000)
	public void testSubscription() throws CloudException{
		initialize();		
		CloudNotification.on("custom-channel",new CloudNotificationCallback(){
			@Override
			public void done(Object x, CloudException t)throws CloudException {
					if(t != null){
						Assert.fail(t.getMessage());
					}
					
					if(x == null){
						Assert.fail("failed to subscribe");
					}
			}	
		});
	}
	
	@Test(timeout=40000)
	public void testPublish()throws CloudException{
		initialize();		
		CloudNotification.on("custom-channel",new CloudNotificationCallback(){
			@Override
			public void done(Object x, CloudException t)throws CloudException {
					if(t != null){
						Assert.fail(t.getMessage());
					}
					
					if(x == null){
						Assert.fail("failed to subscribe");
					}else{
						CloudNotification.publish("custom-channel", "Hello World");
					}
			}	
		});
	}
	
	@Test(timeout=40000)
	public void testUnSubscribe()throws CloudException{
		initialize();		
		CloudNotification.on("custom-channel",new CloudNotificationCallback(){
			@Override
			public void done(Object x, CloudException t)throws CloudException {
				if(t != null){
					Assert.fail(t.getMessage());
				}
				
				if(x == null){
					Assert.fail("failed to subscribe");
				}else{
					CloudNotification.off("custom-channel", new CloudStringCallback(){
						@Override
						public void done(String x, CloudException t)throws CloudException {
							if(t != null){
								Assert.fail(t.getMessage());
							}
						}
					});
				}
			}	
		});
	}
}