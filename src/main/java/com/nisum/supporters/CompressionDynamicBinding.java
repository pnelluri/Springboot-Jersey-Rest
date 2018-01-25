package com.nisum.supporters;

import javax.ws.rs.container.DynamicFeature;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.FeatureContext;

import com.nisum.RestServices.MessageBodyConvertService;

public class CompressionDynamicBinding  implements DynamicFeature {
	 
    @Override
    public void configure(ResourceInfo resourceInfo, FeatureContext context) {
    	System.out.println("CompressionDynamicBinding/DateDynamicBind");
        if (MessageBodyConvertService.class.equals(resourceInfo.getResourceClass())
                && resourceInfo.getResourceMethod()
                    .getName().contains("DateDynamicBind")) {
        	System.out.println("Inside:::CompressionDynamicBinding/DateDynamicBind");
            context.register(GZIPWriterInterceptor.class);
        }
    }

}
