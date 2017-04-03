/*
 * SOFTWARE USE PERMISSION
 *
 * By downloading and accessing this software and associated documentation files ("Software") you are granted the
 * unrestricted right to deal in the Software, including, without limitation the right to use, copy, modify, publish,
 * sublicense and grant such rights to third parties, subject to the following conditions:
 *
 * The following copyright notice and this permission notice shall be included in all copies, modifications or
 * substantial portions of this Software: Copyright © 2016 GSM Association.
 *
 * THE SOFTWARE IS PROVIDED "AS IS," WITHOUT WARRANTY OF ANY KIND, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
 * ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. YOU AGREE TO
 * INDEMNIFY AND HOLD HARMLESS THE AUTHORS AND COPYRIGHT HOLDERS FROM AND AGAINST ANY SUCH LIABILITY.
 */
package com.gsma.mobileconnect.r2.authentication;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.gsma.mobileconnect.r2.utils.ObjectUtils;

import java.net.URI;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Class that holds a valid response from
 * {@link IAuthenticationService#requestTokenAsync(String, String, URI, URI, String)}
 *
 * @since 2.0
 */
@JsonDeserialize(builder = RequestTokenResponseData.Builder.class)
public class RequestTokenResponseData
{
    private final Date timeReceived;
    private final String accessToken;
    private final String tokenType;
    private final String idToken;
    private final String refreshToken;
    private final Date expiry;
    private final Long expiresIn;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("RequestTokenResponseData{");
        sb.append("timeReceived=").append(timeReceived);
        sb.append(", accessToken='").append(accessToken).append('\'');
        sb.append(", tokenType='").append(tokenType).append('\'');
        sb.append(", idToken='").append(idToken).append('\'');
        sb.append(", refreshToken='").append(refreshToken).append('\'');
        sb.append(", expiry=").append(expiry);
        sb.append(", expiresIn=").append(expiresIn);
        sb.append('}');
        return sb.toString();
    }

    private RequestTokenResponseData(final Builder builder)
    {
        this.timeReceived = ObjectUtils.defaultIfNull(builder.timeReceived, new Date());

        this.accessToken = builder.accessToken;
        this.tokenType = builder.tokenType;
        this.idToken = builder.idToken;
        this.refreshToken = builder.refreshToken;
        this.expiresIn = builder.expiresIn;

        this.expiry = this.expiresIn != null ? new Date(
            this.timeReceived.getTime() + TimeUnit.SECONDS.toMillis(this.expiresIn)) : null;
    }

    /**
     * @return the time the response was received.
     */
    public Date getTimeReceived()
    {
        return this.timeReceived;
    }

    /**
     * @return the OAuth 2.0 access token generated by the authorization server.
     */
    public String getAccessToken()
    {
        return this.accessToken;
    }

    /**
     * @return the type of the access token generated.
     */
    public String getTokenType()
    {
        return this.tokenType;
    }

    /**
     * @return additional token used in OIDC to provide the identity token claim.
     */
    public String getIdToken()
    {
        return this.idToken;
    }

    /**
     * @return the optional refresh token generated by the authorization server.
     */
    public String getRefreshToken()
    {
        return this.refreshToken;
    }

    /**
     * @return the time the response will expire.
     */
    public Date getExpiry()
    {
        return this.expiry;
    }

    /**
     * @return the number of seconds from the time received to expiry
     */
    public Long getExpiresIn()
    {
        return this.expiresIn;
    }

    @SuppressWarnings("WeakerAccess")
    public static final class Builder
    {
        private Date timeReceived;
        private String accessToken;
        private String tokenType;
        private String idToken;
        private String refreshToken;
        private Long expiresIn;

        public Builder withTimeReceived(final Date val)
        {
            this.timeReceived = val;
            return this;
        }

        public Builder withAccessToken(final String val)
        {
            this.accessToken = val;
            return this;
        }

        public Builder withTokenType(final String val)
        {
            this.tokenType = val;
            return this;
        }

        public Builder withIdToken(final String val)
        {
            this.idToken = val;
            return this;
        }

        public Builder withRefreshToken(final String val)
        {
            this.refreshToken = val;
            return this;
        }

        public Builder withExpiresIn(final Long val)
        {
            this.expiresIn = val;
            return this;
        }

        public RequestTokenResponseData build()
        {
            return new RequestTokenResponseData(this);
        }
    }
}
