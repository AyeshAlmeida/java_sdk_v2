package com.gsma.mobileconnect.r2.strategy.interfaceHelper;

import com.gsma.mobileconnect.r2.MobileConnectConfig;
import com.gsma.mobileconnect.r2.MobileConnectStatus;
import com.gsma.mobileconnect.r2.discovery.DiscoveryOptions;
import com.gsma.mobileconnect.r2.discovery.IDiscoveryService;
import com.gsma.mobileconnect.r2.utils.KeyValuePair;

/**
 * Created by ayesh on 3/30/17.
 */
public interface WebInterfaceHelperStrategy {
    MobileConnectStatus attemptDiscovery(final IDiscoveryService discoveryService,
                                         final String msisdn,
                                         final String mcc,
                                         final String mnc,
                                         final Iterable<KeyValuePair> cookies,
                                         final MobileConnectConfig config,
                                         final DiscoveryOptions.Builder discoveryOptionsBuilder);

}
