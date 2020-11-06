package deminnik.accessloglistener;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import static org.junit.jupiter.api.Assertions.*;

class AccessLogReaderTest {
    private AccessLogReader accessLogReader;
    private String[] inputStrings;

    @BeforeEach
    void setUp() {
        this.accessLogReader = new AccessLogReader();
    }

    @Test
    void testReadLog() throws UnsupportedEncodingException {
        String[] args = {"-u", "99.9", "-t", "35"};
        AppOptions appOptions = new AppOptions(args);
        StringBuilder builder = new StringBuilder();
        builder.append("192.168.32.181 - - [14/06/2017:16:47:02 +1000] \"PUT /rest/v1.4/documents?zone=default&_rid=6076537c HTTP/1.1\" 200 2 44.510983 \"-\" \"@list-item-updater\" prio:0\n");
        builder.append("192.168.32.181 - - [14/06/2017:16:47:02 +1000] \"PUT /rest/v1.4/documents?zone=default&_rid=7ae28555 HTTP/1.1\" 200 2 23.251219 \"-\" \"@list-item-updater\" prio:0\n");
        builder.append("192.168.32.181 - - [14/06/2017:16:47:02 +1000] \"PUT /rest/v1.4/documents?zone=default&_rid=e356713 HTTP/1.1\" 500 2 30.164372 \"-\" \"@list-item-updater\" prio:0\n");
        String data = builder.toString();
        InputStream testInput = new ByteArrayInputStream( data.getBytes("UTF-8") );
        System.setIn(testInput);
        this.accessLogReader.readLog(appOptions);
        int expected = 1;
        int actual = this.accessLogReader.getRecords().size();
        assertEquals(expected, actual);
    }

    @Test
    void testGetRecords() {
        assertNotNull(this.accessLogReader.getRecords());
    }
}