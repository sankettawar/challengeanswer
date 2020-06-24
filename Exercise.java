
class Exercise 
{ 
  
   
    static int INF = 10000; 
  
    static class Point  
    { 
        int x; 
        int y; 
  
        public Point(int x, int y) 
        { 
            this.x = x; 
            this.y = y; 
        } 
    }; 

    static boolean onSegment(Point p, Point q, Point r)  
    { 
        if (q.x <= Math.max(p.x, r.x) && 
            q.x >= Math.min(p.x, r.x) && 
            q.y <= Math.max(p.y, r.y) && 
            q.y >= Math.min(p.y, r.y)) 
        { 
            return true; 
        } 
        return false; 
    } 
  
    // find ordered triplet (p, q, r). 
    
    static int orientation(Point p, Point q, Point r)  
    { 
        int val = (q.y - p.y) * (r.x - q.x) 
                - (q.x - p.x) * (r.y - q.y); 
  
        if (val == 0)  
        { 
            return 0; // colinear 
        } 
        return (val > 0) ? 1 : 2; // clock or counterclock wise 
    } 
  
    static boolean doIntersect(Point p1, Point q1,  
                               Point p2, Point q2)  
    { 
        // Find the four orientations  
 
        int o1 = orientation(p1, q1, p2); 
        int o2 = orientation(p1, q1, q2); 
        int o3 = orientation(p2, q2, p1); 
        int o4 = orientation(p2, q2, q1); 
  
        // General case 
        if (o1 != o2 && o3 != o4) 
        { 
            return true; 
        } 
  
        // Special Cases 
        
        if (o1 == 0 && onSegment(p1, p2, q1))  
        { 
            return true; 
        } 
  
        // p1, q1 and p2 are colinear and  q2 lies on segment p1q1 
        if (o2 == 0 && onSegment(p1, q2, q1))  
        { 
            return true; 
        } 
  
        // p2, q2 and p1 are colinear and p1 lies on segment p2q2 
        if (o3 == 0 && onSegment(p2, p1, q2)) 
        { 
            return true; 
        } 
  
        // p2, q2 and q1 are colinear and  q1 lies on segment p2q2 
        if (o4 == 0 && onSegment(p2, q1, q2)) 
        { 
            return true; 
        } 
  
        // not in any case
        return false;  
    } 
  
    static boolean isInside(Point polygon[], int n, Point p) 
    { 
        if (n < 3)  
        { 
            return false; 
        } 
  
        // Create a point for line segment from p to infinite 
        Point extreme = new Point(INF, p.y); 
  
        // Count intersections of the above line  
       
        int count = 0, i = 0; 
        do 
        { 
            int next = (i + 1) % n; 
  
            // checking if line segment from p to extreme intersect with line seg from polu[i] to poly[nxt]
			
            if (doIntersect(polygon[i], polygon[next], p, extreme))  
            { 
                //  If it lies, return true, otherwise false 
                if (orientation(polygon[i], p, polygon[next]) == 0) 
                { 
                    return onSegment(polygon[i], p, 
                                     polygon[next]); 
                } 
  
                count++; 
            } 
            i = next; 
        } while (i != 0); 
  
        // Return true if count is odd, false otherwise 
        return (count % 2 == 1); // Same as (count%2 == 1) 
    } 
  
    // Driver Code 
    public static void main(String[] args)  
    { 
        Point polygon1[] = {new Point(1, 0), 
                            new Point(8,3),  
                            new Point(8,8),  
                            new Point(1,5)}; 
        int n = polygon1.length; 
        Point p = new Point(3,5); 
        if (isInside(polygon1, n, p)) 
        { 
            System.out.println("True"); 
        }  
        else 
        { 
            System.out.println("False"); 
        } 
        
        
        Point polygon3[] = {new Point(-3, 2),
                            new Point(-2,-0.8), 
                            new Point(0,1.2), 
                            new Point(2.2,0)
							new Point (2,4.5)}; 
        p = new Point(0,0); 
        n = polygon3.length; 
        if (isInside(polygon3, n, p)) 
        { 
            System.out.println("Yes"); 
        }  
        else 
        { 
            System.out.println("No"); 
        } 
    } 
}
