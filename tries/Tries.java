
import java.util.Stack;

/**
 * Created by yang on 2016/11/24.
 */

public class Tries
{
    private class Node
    {
        public String key;
        public Node left, mid, right, parent;
        public String value;
        public Node(String key)
        {
            this.key = key;
            left = mid = right = parent = null;
            value = null;
        }
        public boolean isLeaf()
        {
            if (left == null && mid == null && right == null)
            {
                return true;
            }
            return false;
        }
        public boolean isKey()
        {
            return (value == null ? false : true);
        }
    }
    private Node root;

    // "/root/yang/top"
    // deep = 0  "/"                => "/" default
    // deep = 1  "/root"            => "root"
    // deep = 2  "/root/yang"       => "yang"
    // deep = 3  "/root/yang/top"   => "top"

    private String[] getKeyList ( String path )
    {
        if (path == null || !path.startsWith("/"))
        {
            return null;
        }
        path = path.substring(1);
        return path.split("/");
    }


    public Node get ( String path )
    {
        String[] keyList = getKeyList(path);
        return get( root, keyList, 0 );
    }

    private Node get ( Node _root, String[] keyList, int deep )
    {
        if ( _root == null )
        {
            return null;
        }

        String curKey = keyList[deep];
        int cmp = curKey.compareTo( _root.key );
        if ( cmp < 0 )
        {
            return get( _root.left, keyList, deep );
        } else if ( 0 < cmp )
        {
            return get( _root.right, keyList, deep );
        } else if ( deep < keyList.length - 1 )
        {
            return get( _root.mid, keyList, deep + 1 );
        } else
        {
            return _root;
        }
    }

    public void put ( String path, String value )
    {
        String[] keyList = getKeyList(path);
        root = put( root, keyList, 0, value );
    }

    private Node put ( Node root, String[] keyList, int deep, String value )
    {
        String curKey = keyList[deep];
        if ( root == null )
        {
            root = new Node( curKey );
        }

        int cmp = curKey.compareTo( root.key );
        if ( cmp < 0 )
        {
            root.left = put( root.left, keyList, deep, value );
        } else if ( 0 < cmp )
        {
            root.right = put( root.right, keyList, deep, value );
        } else if ( deep < keyList.length - 1 )
        {
            root.mid = put( root.mid, keyList, deep + 1, value );
        } else
        {
            // add value
            root.value = value;
            return root;
        }
        return root;
    }

    public void Collection()
    {
        if ( root == null )
        {
            return ;
        }

        Stack<String>  S = new Stack<String>();
        Collection(root, S);

        //String keyList = new String("");
        //DFS(root, keyList);
    }


    //          yang
    //           |
    //          file1
    //           |   \
    //           a 0  file2
    //          /       |
    //         b 2      a 1
    private void DFS ( Node root, String keyList )
    {
        if ( root == null )
        {
            return ;
        }
        if ( root.isLeaf() )
        {

        }
        if ( root.isKey()  )
        {
            System.out.println(keyList + "/" + root.key);
        }

        if ( root.mid != null )
        {
            DFS( root.mid, keyList + "/" + root.key );
        }
        if ( root.left != null )
        {
            DFS( root.left, keyList + "/" + root.left.key );
        }
        if ( root.right != null )
        {
            DFS( root.right, keyList + "/" + root.right.key );
        }
    }

    //          yang
    //           |
    //          file1
    //           |   \
    //           a 0  file2
    //            \      |
    //             b 2   a 1
    private void Collection ( Node root, Stack<String> S )
    {
        S.push( root.key );

        if ( root.isKey() )
        {
            int len = S.size();
            for ( int i = 0; i < len; ++i )
            {
                System.out.print("/" + S.elementAt(i));
            }
            System.out.println(" : " + root.value);
        }

        if ( root.mid != null )
        {
            Collection( root.mid, S );
        }
        if ( root.left != null )
        {
            if ( root.mid == null ) {
                S.pop();
            }
            Collection( root.left, S );
        }
        if ( root.right != null )
        {
            if ( root.left == null && root.mid == null )
            {
                S.pop();
            }
            Collection(root.right, S);
        }

        if ( !S.empty() )
        {
            S.pop();
        }
    }

    public String getValue ( String path )
    {
        String[] keyList = getKeyList(path);
        String value = null;
        if ( keyList == null )
        {
            return value;
        }
        return  getValue(root, keyList);
    }

    private String getValue ( Node root, String[] keyList )
    {
        String value = null;
        for ( int i = 0; root != null ; )
        {
            int cmp = root.key.compareTo(keyList[i]);
            if ( cmp < 0 )
            {
                root = root.right;
            } else if ( 0 < cmp )
            {
                root = root.left;
            } else if ( i < keyList.length - 1 ) {
                root = root.mid;
                ++i;
            } else {
                value = root.value;
                break;
            }
        }
        return value;
    }
}
