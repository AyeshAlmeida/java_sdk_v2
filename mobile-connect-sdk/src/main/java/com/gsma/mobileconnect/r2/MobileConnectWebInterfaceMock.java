package com.gsma.mobileconnect.r2;

import com.gsma.mobileconnect.r2.discovery.DiscoveryOptions;
import com.gsma.mobileconnect.r2.strategy.webInterface.WebInterfaceStrategy;
import com.gsma.mobileconnect.r2.utils.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.event.Level;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by ayesh on 3/30/17.
 */
public class MobileConnectWebInterfaceMock implements WebInterfaceStrategy
{
    private static final Logger LOGGER = LoggerFactory.getLogger(MobileConnectWebInterfaceMock.class);
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

        final DiscoveryOptions.Builder builder =
                options == null ? new DiscoveryOptions.Builder() : options.getDiscoveryOptionsBuilder();

        builder.withClientIp(
                StringUtils.isNullOrEmpty(clientIp) ? HttpUtils.extractClientIp(request) : clientIp);

        final Iterable<KeyValuePair> cookies =
                shouldProxyCookies ? HttpUtils.extractCookiesFromRequest(request) : null;

        LOGGER.debug(
                "Running attemptDiscovery for msisdn={}, mcc={}, mnc={}, shouldProxyCookies={}, clientIp={}",
                LogUtils.mask(msisdn, LOGGER, Level.DEBUG), mcc, mnc, shouldProxyCookies, clientIp);



        return null;
    }
}
