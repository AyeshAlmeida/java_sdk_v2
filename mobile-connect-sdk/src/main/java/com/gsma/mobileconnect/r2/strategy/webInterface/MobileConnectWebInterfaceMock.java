package com.gsma.mobileconnect.r2.strategy.webInterface;

import com.gsma.mobileconnect.r2.MobileConnectRequestOptions;
import com.gsma.mobileconnect.r2.MobileConnectStatus;
import com.gsma.mobileconnect.r2.utils.ObjectUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by ayesh on 3/30/17.
 */
public class MobileConnectWebInterfaceMock implements WebInterfaceStrategy
{
    private static final String ARG_REQUEST = "request";

    @Override
    public MobileConnectStatus attemptDiscovery(HttpServletRequest request,
                                                String msisdn,
                                                String mcc,
                                                String mnc,
                                                boolean shouldProxyCookies,
                                                MobileConnectRequestOptions options)
    {
        ObjectUtils.requireNonNull(request, ARG_REQUEST);

        final String clientIp =
                options == null ? null : options.getDiscoveryOptions().getClientIp();

        //REFACTOR THE CODE
//        final DiscoveryOptions.Builder builder =
//                options == null ? new DiscoveryOptions.Builder() : options.getDiscoveryOptionsBuilder();

        return null;
    }
}
