import java.io.File;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

public class zeroCopy {
    public static void main(String[] args) throws Exception {
        long start = System.currentTimeMillis();
        File srcFile = new File("/Users/sjl/Documents/QiuZhao/手撕代码/code/src/file2.txt");
        File desFile = new File("/Users/sjl/Documents/QiuZhao/手撕代码/code/src/file3.txt");
        FileChannel srcFileChannel = new RandomAccessFile(srcFile, "r").getChannel();
        FileChannel desFileChannel = new RandomAccessFile(desFile, "rw").getChannel();
        srcFileChannel.transferTo(0, srcFile.length(), desFileChannel);
        long end = System.currentTimeMillis();
        System.out.println(String.format("%.4f", (float)(end - start) / 1000));
    }
}
