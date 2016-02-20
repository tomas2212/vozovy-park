package cz.muni.fi.pa165.vozovypark.carparkclient;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.DeleteMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.InputStreamRequestEntity;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.PutMethod;

public class ProxyServlet extends HttpServlet {

    /**
     * Serialization UID.
     */
    private static final long serialVersionUID = 1L;
    /**
     * Key for redirect location header.
     */
    private static final String STRING_LOCATION_HEADER = "Location";
    /**
     * Key for content type header.
     */
    private static final String STRING_CONTENT_TYPE_HEADER_NAME = "Content-Type";
    /**
     * Key for content length header.
     */
    private static final String STRING_CONTENT_LENGTH_HEADER_NAME = "Content-Length";
    /**
     * Key for host header
     */
    private static final String STRING_HOST_HEADER_NAME = "Host";
    /**
     * The directory to use to temporarily store uploaded files
     */
    // Proxy host params
    /**
     * The host to which we are proxying requests
     */
    private String stringProxyHost;
    /**
     * The port on the proxy host to which we are proxying requests. Default
     * value is 80.
     */
    private int intProxyPort = 80;
    /**
     * The (optional) path on the proxy host to which we are proxying requests.
     * Default value is "".
     */
    private String stringProxyPath = "";
    /**
     * The maximum size for uploaded files in bytes. Default value is 5MB.
     */
    private int intMaxFileUploadSize = 5 * 1024 * 1024;

    /**
     * Initialize the <code>ProxyServlet</code>
     *
     * @param servletConfig The Servlet configuration passed in by the servlet
     * container
     */
    @Override
    public void init(ServletConfig servletConfig) {
        // Get the proxy host
        String stringProxyHostNew = servletConfig.getInitParameter("proxyHost");
        if (stringProxyHostNew == null || stringProxyHostNew.length() == 0) {
            throw new IllegalArgumentException("Proxy host not set, please set init-param 'proxyHost' in web.xml");
        }
        this.setProxyHost(stringProxyHostNew);
        // Get the proxy port if specified
        String stringProxyPortNew = servletConfig.getInitParameter("proxyPort");
        if (stringProxyPortNew != null && stringProxyPortNew.length() > 0) {
            this.setProxyPort(Integer.parseInt(stringProxyPortNew));
        }
        // Get the proxy path if specified
        String stringProxyPathNew = servletConfig.getInitParameter("proxyPath");
        if (stringProxyPathNew != null && stringProxyPathNew.length() > 0) {
            this.setProxyPath(stringProxyPathNew);
        }
        // Get the maximum file upload size if specified
        String stringMaxFileUploadSize = servletConfig.getInitParameter("maxFileUploadSize");
        if (stringMaxFileUploadSize != null && stringMaxFileUploadSize.length() > 0) {
            this.setMaxFileUploadSize(Integer.parseInt(stringMaxFileUploadSize));
        }
    }

    /**
     * Performs an HTTP GET request
     *
     * @param httpServletRequest The {@link HttpServletRequest} object passed in
     * by the servlet engine representing the client request to be proxied
     * @param httpServletResponse The {@link HttpServletResponse} object by
     * which we can send a proxied response to the client
     * @throws java.io.IOException
     * @throws javax.servlet.ServletException
     */
    @Override
    public void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
            throws IOException, ServletException {
        // Create a GET request
        GetMethod getMethodProxyRequest = new GetMethod(this.getProxyURL(httpServletRequest));
        // Forward the request headers
        setProxyRequestHeaders(httpServletRequest, getMethodProxyRequest);
        // Execute the proxy request
        this.executeProxyRequest(getMethodProxyRequest, httpServletRequest, httpServletResponse);
    }

    @Override
    public void doDelete(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
            throws IOException, ServletException {
        // Create a GET request
        DeleteMethod getMethodProxyRequest = new DeleteMethod(this.getProxyURL(httpServletRequest));
        // Forward the request headers
        setProxyRequestHeaders(httpServletRequest, getMethodProxyRequest);
        // Execute the proxy request
        this.executeProxyRequest(getMethodProxyRequest, httpServletRequest, httpServletResponse);
    }

    /**
     * Performs an HTTP POST request
     *
     * @param httpServletRequest The {@link HttpServletRequest} object passed in
     * by the servlet engine representing the client request to be proxied
     * @param httpServletResponse The {@link HttpServletResponse} object by
     * which we can send a proxied response to the client
     * @throws java.io.IOException
     * @throws javax.servlet.ServletException
     */
    @Override
    public void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
            throws IOException, ServletException {
        // Create a standard POST request
        PostMethod postMethodProxyRequest = new PostMethod(this.getProxyURL(httpServletRequest));
        // Forward the request headers
        setProxyRequestHeaders(httpServletRequest, postMethodProxyRequest);
        this.handleStandardPost(postMethodProxyRequest, httpServletRequest);
        // Execute the proxy request
        this.executeProxyRequest(postMethodProxyRequest, httpServletRequest, httpServletResponse);
    }

    @Override
    public void doPut(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
            throws IOException, ServletException {
        // Create a standard POST request
        PutMethod putMethodProxyRequest = new PutMethod(this.getProxyURL(httpServletRequest));
        // Forward the request headers
        setProxyRequestHeaders(httpServletRequest, putMethodProxyRequest);
        this.handleStandardPut(putMethodProxyRequest, httpServletRequest);
        // Execute the proxy request
        this.executeProxyRequest(putMethodProxyRequest, httpServletRequest, httpServletResponse);
    }

    /**
     * Sets up the given {@link PostMethod} to send the same multipart POST data
     * as was sent in the given {@link HttpServletRequest}
     *
     * @param postMethodProxyRequest The {@link PostMethod} that we are
     * configuring to send a multipart POST request
     * @param httpServletRequest The {@link HttpServletRequest} that contains
     * the mutlipart POST data to be sent via the {@link PostMethod}
     */
    /**
     * Sets up the given {@link PostMethod} to send the same standard POST data
     * as was sent in the given {@link HttpServletRequest}
     *
     * @param postMethodProxyRequest The {@link PostMethod} that we are
     * configuring to send a standard POST request
     * @param httpServletRequest The {@link HttpServletRequest} that contains
     * the POST data to be sent via the {@link PostMethod}
     */
    @SuppressWarnings("unchecked")
    private void handleStandardPost(PostMethod postMethodProxyRequest, HttpServletRequest httpServletRequest) throws IOException {
        postMethodProxyRequest.setRequestEntity(new InputStreamRequestEntity(httpServletRequest.getInputStream()));
    }

    @SuppressWarnings("unchecked")
    private void handleStandardPut(PutMethod putMethodProxyRequest, HttpServletRequest httpServletRequest) throws IOException {
        putMethodProxyRequest.setRequestEntity(new InputStreamRequestEntity(httpServletRequest.getInputStream()));
    }

    /**
     * Executes the {@link HttpMethod} passed in and sends the proxy response
     * back to the client via the given {@link HttpServletResponse}
     *
     * @param httpMethodProxyRequest An object representing the proxy request to
     * be made
     * @param httpServletResponse An object by which we can send the proxied
     * response back to the client
     * @throws IOException Can be thrown by the {@link HttpClient}.executeMethod
     * @throws ServletException Can be thrown to indicate that another error has
     * occurred
     */
    private void executeProxyRequest(
            HttpMethod httpMethodProxyRequest,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse)
            throws IOException, ServletException {
        // Create a default HttpClient
        HttpClient httpClient = new HttpClient();
        httpMethodProxyRequest.setFollowRedirects(false);
        // Execute the request
        int intProxyResponseCode = httpClient.executeMethod(httpMethodProxyRequest);

        // Check if the proxy response is a redirect
        // The following code is adapted from org.tigris.noodle.filters.CheckForRedirect
        // Hooray for open source software
        if (intProxyResponseCode >= HttpServletResponse.SC_MULTIPLE_CHOICES /* 300 */
                && intProxyResponseCode < HttpServletResponse.SC_NOT_MODIFIED /* 304 */) {
            String stringStatusCode = Integer.toString(intProxyResponseCode);
            String stringLocation = httpMethodProxyRequest.getResponseHeader(STRING_LOCATION_HEADER).getValue();
            if (stringLocation == null) {
                throw new ServletException("Recieved status code: " + stringStatusCode
                        + " but no " + STRING_LOCATION_HEADER + " header was found in the response");
            }
            // Modify the redirect to go to this proxy servlet rather that the proxied host
            String stringMyHostName = httpServletRequest.getServerName();
            if (httpServletRequest.getServerPort() != 80) {
                stringMyHostName += ":" + httpServletRequest.getServerPort();
            }
            stringMyHostName += httpServletRequest.getContextPath();
            httpServletResponse.sendRedirect(stringLocation.replace(getProxyHostAndPort() + this.getProxyPath(), stringMyHostName));
            return;
        } else if (intProxyResponseCode == HttpServletResponse.SC_NOT_MODIFIED) {
            // 304 needs special handling.  See:
            // http://www.ics.uci.edu/pub/ietf/http/rfc1945.html#Code304
            // We get a 304 whenever passed an 'If-Modified-Since'
            // header and the data on disk has not changed; server
            // responds w/ a 304 saying I'm not going to send the
            // body because the file has not changed.
            httpServletResponse.setIntHeader(STRING_CONTENT_LENGTH_HEADER_NAME, 0);
            httpServletResponse.setStatus(HttpServletResponse.SC_NOT_MODIFIED);
            return;
        }

        // Pass the response code back to the client
        httpServletResponse.setStatus(intProxyResponseCode);

        // Pass response headers back to the client
        Header[] headerArrayResponse = httpMethodProxyRequest.getResponseHeaders();
        for (Header header : headerArrayResponse) {
            httpServletResponse.setHeader(header.getName(), header.getValue());
        }

        // Send the content to the client
        InputStream inputStreamProxyResponse = httpMethodProxyRequest.getResponseBodyAsStream();
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStreamProxyResponse);
        OutputStream outputStreamClientResponse = httpServletResponse.getOutputStream();
        int intNextByte;
        while ((intNextByte = bufferedInputStream.read()) != -1) {
            outputStreamClientResponse.write(intNextByte);
        }
    }

    @Override
    public String getServletInfo() {
        return "Jason's Proxy Servlet";
    }

    /**
     * Retrieves all of the headers from the servlet request and sets them on
     * the proxy request
     *
     * @param httpServletRequest The request object representing the client's
     * request to the servlet engine
     * @param httpMethodProxyRequest The request that we are about to send to
     * the proxy host
     */
    @SuppressWarnings("unchecked")
    private void setProxyRequestHeaders(HttpServletRequest httpServletRequest, HttpMethod httpMethodProxyRequest) {
        // Get an Enumeration of all of the header names sent by the client
        Enumeration enumerationOfHeaderNames = httpServletRequest.getHeaderNames();
        while (enumerationOfHeaderNames.hasMoreElements()) {
            String stringHeaderName = (String) enumerationOfHeaderNames.nextElement();
            if (stringHeaderName.equalsIgnoreCase(STRING_CONTENT_LENGTH_HEADER_NAME)) {
                continue;
            }
            // As per the Java Servlet API 2.5 documentation:
            //		Some headers, such as Accept-Language can be sent by clients
            //		as several headers each with a different value rather than
            //		sending the header as a comma separated list.
            // Thus, we get an Enumeration of the header values sent by the client
            Enumeration enumerationOfHeaderValues = httpServletRequest.getHeaders(stringHeaderName);
            while (enumerationOfHeaderValues.hasMoreElements()) {
                String stringHeaderValue = (String) enumerationOfHeaderValues.nextElement();
                // In case the proxy host is running multiple virtual servers,
                // rewrite the Host header to ensure that we get content from
                // the correct virtual server
                if (stringHeaderName.equalsIgnoreCase(STRING_HOST_HEADER_NAME)) {
                    stringHeaderValue = getProxyHostAndPort();
                }
                Header header = new Header(stringHeaderName, stringHeaderValue);
                // Set the same header on the proxy request
                httpMethodProxyRequest.setRequestHeader(header);
            }
        }
    }

    // Accessors
    private String getProxyURL(HttpServletRequest httpServletRequest) {
        // Set the protocol to HTTP
        String stringProxyURL = "http://" + this.getProxyHostAndPort();
        // Check if we are proxying to a path other that the document root
        if (!this.getProxyPath().equalsIgnoreCase("")) {
            stringProxyURL += this.getProxyPath();
        }
        // Handle the path given to the servlet
        stringProxyURL += httpServletRequest.getPathInfo();
        // Handle the query string
        if (httpServletRequest.getQueryString() != null) {
            stringProxyURL += "?" + httpServletRequest.getQueryString();
        }
        return stringProxyURL;
    }

    private String getProxyHostAndPort() {
        if (this.getProxyPort() == 80) {
            return this.getProxyHost();
        } else {
            return this.getProxyHost() + ":" + this.getProxyPort();
        }
    }

    private String getProxyHost() {
        return this.stringProxyHost;
    }

    private void setProxyHost(String stringProxyHostNew) {
        this.stringProxyHost = stringProxyHostNew;
    }

    private int getProxyPort() {
        return this.intProxyPort;
    }

    private void setProxyPort(int intProxyPortNew) {
        this.intProxyPort = intProxyPortNew;
    }

    private String getProxyPath() {
        return this.stringProxyPath;
    }

    private void setProxyPath(String stringProxyPathNew) {
        this.stringProxyPath = stringProxyPathNew;
    }

    private int getMaxFileUploadSize() {
        return this.intMaxFileUploadSize;
    }

    private void setMaxFileUploadSize(int intMaxFileUploadSizeNew) {
        this.intMaxFileUploadSize = intMaxFileUploadSizeNew;
    }
}
