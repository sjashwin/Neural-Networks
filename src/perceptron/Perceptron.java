/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package perceptron;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author ashwin
 */
public class Perceptron {
    static int[][] inputs = {
        {1,1} ,
        {1,0} ,
        {0,1} ,
        {0,0} } ; 
    static int[] target = { 1, -1, -1, -1 } ;
    static float bias ;
    static float out ;
    static float w1, w2, b_new ;
    static boolean stopping ;
    static float threshold ;
    static float alpha ;
    static float y_in ;
    static float output  ;
    static int epoch ;
    static FileWriter writer ;
    static File file ;
    /**
     * @param args the command line arguments
     */
    Perceptron(float threshold, float alpha) throws IOException{
        // Constructor .
        bias = 1.0f ;
        w1 = w2 = b_new = out = y_in = output = 0.0f ;
        stopping = false ;
        Perceptron.threshold = threshold ;
        Perceptron.alpha = alpha ;
        epoch = 0 ;
    }
    public static int execute()throws IOException{
        while( !stopping && epoch <= 20 ) {
           stopping = true ;
           epoch++ ;
            for( int i=0 ;i<4; i++){
                y_in = b_new + ( ( inputs[i][0]*w1 )+( inputs[i][1]*w2 ) ) ;
                if(y_in > threshold )
                    output = 1 ;
                else if( y_in < -threshold )
                    output = -1 ;
                else
                    output = 0 ;
                if( output != target[i] ){
                    w1 = ( w1+( alpha*target[i]*inputs[i][0] ) ) ;
                    w2 = ( w2+( alpha*target[i]*inputs[i][1] ) ) ;
                    b_new = (int)( b_new + ( alpha*target[i] ) ) ;
                    stopping = false ;
                }
                System.out.println( inputs[i][0]+"\t"+inputs[i][1]+"\t"+bias+"\t"+(int)y_in+"\t"+output+"\t"+target[i]+"\t"+
                        w1+"\t"+w2+"\t"+b_new) ;
            }
            System.out.println("-------------------------------------------") ;
        }
       System.out.println("Total number of epochs : "+epoch );
       return epoch ;
        }
    /**
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args)throws IOException {
        file = new File( "/home/ashwin/Desktop/float_bias.csv" ) ;
        writer = new FileWriter( file ) ;
        writer.append( "threshold/learning_rate," ) ;
        for( int i=10 ;i<=100; i++){
            alpha = i/10f ;
            writer.write(String.valueOf(alpha)+",");
        }
        writer.write("\n");
        for( int i=0; i<=5; i++){
            threshold = i/10f ;
            writer.append(String.valueOf(threshold+","));
            for( int j=10 ;j<=100; j++){
                alpha = j/10f ;
                System.out.println( threshold ) ;
                new Perceptron(threshold, alpha); 
                epoch = Perceptron.execute() ;
                writer.append(((epoch<20)?epoch:Double.POSITIVE_INFINITY)+",");
            }
            writer.append("\n") ;
        }
        writer.close();
    }
}
