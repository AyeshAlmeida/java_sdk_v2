package com.gsma.mobileconnect.r2;

import com.gsma.mobileconnect.r2.discovery.DiscoveryOptions;
import com.gsma.mobileconnect.r2.discovery.DiscoveryResponse;
import com.gsma.mobileconnect.r2.discovery.IDiscoveryService;
import com.gsma.mobileconnect.r2.utils.KeyValuePair;
import com.gsma.mobileconnect.r2.utils.LogUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.event.Level;

import java.net.URI;

/**
 * Created by ayesh on 3/30/17.
 */
public class MobileConnectInterfaceHelperMock
{
    private final static Logger LOGGER = LoggerFactory.getLogger(MobileConnectInterfaceHelperMock.class);

    public static MobileConnectStatus attemptDiscovery(IDiscoveryService discoveryService,
                                                String msisdn,
                                                String mcc,
                                                String mnc,
                                                Iterable<KeyValuePair> cookies,
                                                MobileConnectConfig config,
                                                DiscoveryOptions.Builder discoveryOptionsBuilder)
    {
        try
        {
            discoveryOptionsBuilder
                    .withMsisdn(msisdn)
                    .withIdentifiedMcc(mcc)
                    .withIdentifiedMnc(mnc)
                    .withRedirectUrl(config.getRedirectUrl());

            final DiscoveryResponse response = DiscoveryServiceMock.startAutomatedOperatorDiscovery(
                    config.getClientId(),
                    config.getClientSecret(),
                    config.getRedirectUrl(),
                    discoveryOptionsBuilder.build(),
                    cookies);
            return null;
        }
        catch (final Exception e)
        {
            LOGGER.warn("attemptDiscovery failed for msisdn={}, mcc={}, mnc={}",
                    LogUtils.mask(msisdn, LOGGER, Level.WARN), mcc, mnc, e);
            return MobileConnectStatus.error("start automated discovery", e);
        }
    }
}
