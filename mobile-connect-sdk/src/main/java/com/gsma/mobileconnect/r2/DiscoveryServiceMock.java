package com.gsma.mobileconnect.r2;

import com.gsma.mobileconnect.r2.constants.Parameters;
import com.gsma.mobileconnect.r2.discovery.DiscoveryOptions;
import com.gsma.mobileconnect.r2.discovery.DiscoveryResponse;
import com.gsma.mobileconnect.r2.encoding.DefaultEncodeDecoder;
import com.gsma.mobileconnect.r2.encoding.IMobileConnectEncodeDecoder;
import com.gsma.mobileconnect.r2.rest.RestAuthentication;
import com.gsma.mobileconnect.r2.utils.HttpUtils;
import com.gsma.mobileconnect.r2.utils.KeyValuePair;
import com.gsma.mobileconnect.r2.utils.ObjectUtils;
import com.gsma.mobileconnect.r2.utils.StringUtils;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

/**
 * Created by ayesh on 3/31/17.
 */
public class DiscoveryServiceMock
{
    private static final List<String> REQUIRED_COOKIES =
            Arrays.asList("ENUM_NONCE", "MOST_RECENT_SELECTED_OPERATOR",
                    "MOST_RECENT_SELECTED_OPERATOR_EXPIRY");
    private static final IMobileConnectEncodeDecoder iMobileConnectEncodeDecoder = new DefaultEncodeDecoder();

    public static DiscoveryResponse startAutomatedOperatorDiscovery(final String clientId,
                                                             final String clientSecret,
                                                             final URI redirectUrl,
                                                             final DiscoveryOptions options,
                                                             final Iterable<KeyValuePair> currentCookies)
    {
        final DiscoveryOptions.Builder builder =
                new DiscoveryOptions.Builder(options).withRedirectUrl(redirectUrl);

        StringUtils.requireNonEmpty(clientId, "clientId");
        StringUtils.requireNonEmpty(clientSecret, "clientSecret");
        ObjectUtils.requireNonNull(options, "options");
        ObjectUtils.requireNonNull(options.getRedirectUrl(), "options.redirectUrl");

        final Iterable<KeyValuePair> cookies =
                HttpUtils.proxyRequired(REQUIRED_COOKIES, currentCookies);
        final RestAuthentication authentication =
                RestAuthentication.basic(clientId, clientSecret, iMobileConnectEncodeDecoder);
        final List<KeyValuePair> queryParams = extractQueryParams(options);


        return null;
    }

    private static List<KeyValuePair> extractQueryParams(final DiscoveryOptions options)
    {
        KeyValuePair.ListBuilder listBuilder = new KeyValuePair.ListBuilder()
                .add(Parameters.REDIRECT_URL, options.getRedirectUrl().toString())
                .add(Parameters.IDENTIFIED_MCC, options.getIdentifiedMcc())
                .add(Parameters.IDENTIFIED_MNC, options.getIdentifiedMnc())
                .add(Parameters.SELECTED_MCC, options.getSelectedMcc())
                .add(Parameters.SELECTED_MNC, options.getSelectedMnc())
                .add(Parameters.LOCAL_CLIENT_IP, options.getLocalClientIp())
                .add(Parameters.USING_MOBILE_DATA, options.isUsingMobileData() ? "1" : "0");

        if (options.getMsisdn() != null)
        {
            listBuilder.add(Parameters.MSISDN, StringUtils.trimLeading(options.getMsisdn(), '+'));
        }

        return listBuilder.build();
    }


}
