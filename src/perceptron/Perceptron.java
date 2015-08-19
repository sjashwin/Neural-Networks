/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package perceptron;

/**
 *
 * @author ashwin
 */
public class Perceptron {
    static int[][] inputs = {{1,1},{1,0},{0,1},{0,0}} ; 
    static int[] target = {1,-1,-1,-1} ;
    static int bias ;
    static int out ;
    static int w1, w2, b_new ;
    static boolean stopping ;
    static float threshold ;
    static float alpha ;
    static float y_in ;
    static int output  ;
    static int epoch ;
    /**
     * @param args the command line arguments
     */
    Perceptron(){
        bias = 1 ;
        out = 0 ;
        w1 = w2 = b_new = 0 ;
        stopping = false ;
        threshold = 0.2f ;
        alpha = 1.0f ;
        y_in = 0.0f ;
        output= 0 ;
        epoch = 0 ;
    }
    public static void main(String[] args) {
        // TODO code application logic here
        new Perceptron() ;
        
       while( !stopping ) {
           stopping = true ;
           epoch++ ;
            for( int i=0 ;i<4; i++){
                y_in = b_new+((inputs[i][0]*w1)+(inputs[i][1]*w2)) ;
                if(y_in > threshold ){
                    output = 1 ;
                }
                else if( y_in < -threshold ){
                    output = -1 ;
                }
                else
                    output = 0 ;
                if( output != target[i] ){
                    w1 = w1+((int)alpha*target[i]*inputs[i][0]) ;
                    w2 = w2+((int)alpha*target[i]*inputs[i][1]) ;
                    b_new = b_new + ((int)alpha*target[i]) ;
                    stopping = false ;
                }
                System.out.println( inputs[i][0]+"\t"+inputs[i][1]+"\t"+bias+"\t"+(int)y_in+"\t"+output+"\t"+target[i]+"\t"+
                        w1+"\t"+w2+"\t"+b_new) ;
            }
            System.out.println("-------------------------------------------") ;
        }
       System.out.println("Total number of epochs : "+epoch );
    }
}
