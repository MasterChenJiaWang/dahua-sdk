package com.daren.chen.dahua.api.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.nio.charset.StandardCharsets;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.ResponseHandler;

/**
 * @Description:
 * @author: chendaren
 * @CreateDate: 2020/8/5 16:12
 */
public class HandleResponseImpl implements ResponseHandler<ResponseDto> {

    @Override
    public ResponseDto handleResponse(HttpResponse httpResponse) throws IOException {
        ResponseDto response = new ResponseDto();
        if (null != httpResponse) {
            response.setResponse(httpResponse);
            response.setStatusCode(httpResponse.getStatusLine().getStatusCode());
            for (Header header : httpResponse.getAllHeaders()) {
                response.setHeader(header.getName(), MessageDigestUtil.iso88591ToUtf8(header.getValue()));
            }
            response.setContentType(response.getHeader("Content-Type"));
            response.setRequestId(response.getHeader("X-Ca-Request-Id"));
            response.setErrorMessage(response.getHeader("X-Ca-Error-Message"));
            if (httpResponse.getEntity() == null) {
                response.setBody(null);
            } else {
                String s = readStreamAsStr(httpResponse.getEntity().getContent());
                response.setBody(s);
                response.setErrorMessage(s);
            }
        } else {
            response.setStatusCode(500);
            response.setErrorMessage("No Response");
        }
        return response;
    }

    public static String readStreamAsStr(InputStream is) throws IOException {
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
            WritableByteChannel dest = Channels.newChannel(bos); ReadableByteChannel src = Channels.newChannel(is);) {
            ByteBuffer bb = ByteBuffer.allocate(4096);
            while (src.read(bb) != -1) {
                bb.flip();
                dest.write(bb);
                bb.clear();
            }
            return new String(bos.toByteArray(), StandardCharsets.UTF_8);
        }

    }
}
