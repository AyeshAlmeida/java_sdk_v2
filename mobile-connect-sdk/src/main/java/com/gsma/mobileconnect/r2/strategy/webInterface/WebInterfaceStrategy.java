package com.gsma.mobileconnect.r2.strategy.webInterface;

import com.gsma.mobileconnect.r2.MobileConnectRequestOptions;
import com.gsma.mobileconnect.r2.MobileConnectStatus;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;

/**
 * Created by ayesh on 3/30/17.
 */
public interface WebInterfaceStrategy {
    MobileConnectStatus attemptDiscovery(final HttpServletRequest request,
                                         final String msisdn,
                                         final String mcc,
                                         final String mnc,
                                         String clientId,
                                         String clientSecret,
                                         URI redirectUri,
                                         final boolean shouldProxyCookies,
                                         final MobileConnectRequestOptions options);

}
