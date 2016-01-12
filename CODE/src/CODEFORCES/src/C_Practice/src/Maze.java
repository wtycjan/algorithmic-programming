import java.io.*;
import java.util.*;

/**
 * Created by Shreyans Sheth [bholagabbar] on 12/20/2015 at 5:53 PM using IntelliJ IDEA (Fast IO Template)
 */

public class Maze
{
    static class Point
    {
        int c1,c2;
        Point(int c1, int c2)//current char, coords
        {
            this.c1=c1;
            this.c2=c2;
        }
    }

    static char[][]grid;
    static boolean[][] vis;
    static boolean[][] status;
    static int[] dx={1,-1,0,0};
    static int[] dy={0,0,1,-1};
    static int r,c,k, dots;

    static void gridBFS(int x, int y)
    {
        Queue<Point> q=new LinkedList<Point>();
        q.add(new Point(x, y));
        vis[x][y]=true;
        int cnt=0;
        while(!q.isEmpty())
        {
            Point X=q.poll();
            int c1=X.c1;
            int c2=X.c2;
            for(int i=0;i<4;i++){
                if(c1+dx[i]>=0 && c1+dx[i]<r && c2+dy[i]>=0 && c2+dy[i]<c && !vis[c1+dx[i]][c2+dy[i]] && grid[c1+dx[i]][c2+dy[i]]=='.') {
                    q.add(new Point(c1 + dx[i], c2 + dy[i]));
                    vis[c1+dx[i]][c2+dy[i]]=true;
                }
            }
            cnt++;
            status[c1][c2]=true;
            if(cnt==dots-k)
                return;


        }
    }


    public static void main(String[] args) throws Exception
    {
        //System.setIn(new FileInputStream("E:/Shreyans/Documents/Code/CODE/src/Stdin_File_Read.txt"));
        InputReader in = new InputReader(System.in);
        OutputWriter out = new OutputWriter(System.out);
        r=in.readInt();
        c=in.readInt();
        k=in.readInt();
        dots=0;
        grid=new char[r+1][c+1];
        vis=new boolean[r+1][c+1];
        status=new boolean[r+1][c+1];
        int startX=0, startY=0;
        for(int i=0; i<r; i++){
            grid[i]=in.readString().toCharArray();
            for(int j=0; j<c ; j++){
                if(grid[i][j]=='.') {
                    startX=i;
                    startY=j;
                    dots++;
                }
            }
        }
        gridBFS(startX, startY);
        for(int i=0;i<r;i++){
            for(int j=0; j<c; j++){
                if(grid[i][j]=='.' && !status[i][j])
                    grid[i][j]='X';
                out.print(grid[i][j]);
            }
            out.printLine();
        }
    }

    //FAST IO
    private static class InputReader
    {
        private InputStream stream;
        private byte[] buf = new byte[1024];
        private int curChar;
        private int numChars;
        private SpaceCharFilter filter;

        public InputReader(InputStream stream)
        {
            this.stream = stream;
        }

        public int read()
        {
            if (numChars == -1)
                throw new InputMismatchException();
            if (curChar >= numChars) {
                curChar = 0;
                try {
                    numChars = stream.read(buf);
                } catch (IOException e) {
                    throw new InputMismatchException();
                }
                if (numChars <= 0)
                    return -1;
            }
            return buf[curChar++];
        }

        public int readInt()
        {
            int c = read();
            while (isSpaceChar(c))
                c = read();
            int sgn = 1;
            if (c == '-') {
                sgn = -1;
                c = read();
            }
            int res = 0;
            do {
                if (c < '0' || c > '9')
                    throw new InputMismatchException();
                res *= 10;
                res += c - '0';
                c = read();
            } while (!isSpaceChar(c));
            return res * sgn;
        }

        public String readString()
        {
            int c = read();
            while (isSpaceChar(c))
                c = read();
            StringBuilder res = new StringBuilder();
            do {
                res.appendCodePoint(c);
                c = read();
            } while (!isSpaceChar(c));
            return res.toString();
        }

        public double readDouble()
        {
            int c = read();
            while (isSpaceChar(c))
                c = read();
            int sgn = 1;
            if (c == '-') {
                sgn = -1;
                c = read();
            }
            double res = 0;
            while (!isSpaceChar(c) && c != '.') {
                if (c == 'e' || c == 'E')
                    return res * Math.pow(10, readInt());
                if (c < '0' || c > '9')
                    throw new InputMismatchException();
                res *= 10;
                res += c - '0';
                c = read();
            }
            if (c == '.') {
                c = read();
                double m = 1;
                while (!isSpaceChar(c)) {
                    if (c == 'e' || c == 'E')
                        return res * Math.pow(10, readInt());
                    if (c < '0' || c > '9')
                        throw new InputMismatchException();
                    m /= 10;
                    res += (c - '0') * m;
                    c = read();
                }
            }
            return res * sgn;
        }

        public long readLong()
        {
            int c = read();
            while (isSpaceChar(c))
                c = read();
            int sgn = 1;
            if (c == '-') {
                sgn = -1;
                c = read();
            }
            long res = 0;
            do {
                if (c < '0' || c > '9')
                    throw new InputMismatchException();
                res *= 10;
                res += c - '0';
                c = read();
            } while (!isSpaceChar(c));
            return res * sgn;
        }

        public boolean isSpaceChar(int c)
        {
            if (filter != null)
                return filter.isSpaceChar(c);
            return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
        }

        public String next()
        {
            return readString();
        }

        public interface SpaceCharFilter
        {
            public boolean isSpaceChar(int ch);
        }
    }

    private static class OutputWriter
    {
        private final PrintWriter writer;

        public OutputWriter(OutputStream outputStream)
        {
            writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(outputStream)));
        }

        public OutputWriter(Writer writer)
        {
            this.writer = new PrintWriter(writer);
        }

        public void print(Object... objects)
        {
            for (int i = 0; i < objects.length; i++) {
                if (i != 0)
                    writer.print(' ');
                writer.print(objects[i]);
            }
            writer.flush();
        }

        public void printLine(Object... objects)
        {
            print(objects);
            writer.println();
            writer.flush();
        }

        public void close()
        {
            writer.close();
        }

        public void flush()
        {
            writer.flush();
        }
    }
}