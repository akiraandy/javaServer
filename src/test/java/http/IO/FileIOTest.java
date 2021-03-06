package http.IO;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;


class FileIOTest {
    private IFileIO IFileIO;
    @BeforeEach
    void setUp() {
        IFileIO = new FileIO("./public");
    }

    @Test
    void exists() {
        assertFalse(IFileIO.exists("/foo"));
        assertTrue(IFileIO.exists("/file1"));
    }

    @Test
    void isFile() {
        assertFalse(IFileIO.isFile("/foo"));
        assertTrue(IFileIO.isFile("/text-file.txt"));
    }

    @Test
    void isDirectory() {
        assertTrue(IFileIO.isDirectory("/"));
        assertFalse(IFileIO.isDirectory("/foo"));
    }

    @Test
    void readFile() throws IOException {
        assertEquals(4, IFileIO.readFile("/partial_content.txt", 0, 4).length);
    }
}