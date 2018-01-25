package com.nisum;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import com.nisum.RestServices.LifeCycleService;
import com.nisum.RestServices.ParamConvertService;
import com.nisum.RestServices.RestService;
import com.nisum.RestServices.VersionTest;
import com.nisum.supporters.CompressionDynamicBinding;
import com.nisum.supporters.DateMessageBodyReader;
import com.nisum.supporters.DateMessageBodywriter;
import com.nisum.supporters.GZIPWriterInterceptor;
import com.nisum.supporters.MyConverter;
import com.nisum.supporters.MyExceptionMapper;
import com.nisum.supporters.ShortDateMessageBodywriter;
@Component
public class JerseyConfig extends ResourceConfig
{
    public JerseyConfig()
    {

        register(RestService.class);
        register(LifeCycleService.class);
        register(ParamConvertService.class);
        register(new MyConverter());
        register(ParamConvertService.class);
        register(DateMessageBodywriter.class);

        register(ShortDateMessageBodywriter.class);
        register(DateMessageBodyReader.class);
        register(CompressionDynamicBinding.class);

        register(VersionTest.class);
        
        register(new MyExceptionMapper());
        packages("com.nisum.RestServices");
       packages("com.nisum.supporters");       
        
    }
}