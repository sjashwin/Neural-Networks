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
    static float out, threshold, alpha, y_in, output, w1, w2, b_new, bias ;
    static boolean stopping ;
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
    /**
     * 
     * @return 
     * @throws IOException
    */
    public static int execute()throws IOException{
        /*
        * Runs each epoch.
        * While loop breaks after all the targets same as the net values.
        */
        while( !stopping && epoch <= 20 ) {
           stopping = true ;
           epoch++ ;
            for( int i=0 ;i<4; i++){
                y_in = b_new + ( ( inputs[i][0]*w1 )+( inputs[i][1]*w2 ) ) ; //net = b(new)+((x1*w1)+(x2*w2))
                if(y_in > threshold )
                    output = 1 ;
                else if( y_in < -threshold )
                    output = -1 ;
                else
                    output = 0 ;
                if( output != target[i] ){
                    w1 = ( w1+( alpha*target[i]*inputs[i][0] ) ) ; // w(new) = w(old)+(alpha*target*x)
                    w2 = ( w2+( alpha*target[i]*inputs[i][1] ) ) ;
                    b_new = (int)( b_new + ( alpha*target[i] ) ) ; //b(new) = (old)+(alpha*x)
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
        file = new File( "float_bias.csv" ) ;
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
                new Perceptron(threshold, alpha); //Creates a new Perceptron.
                epoch = Perceptron.execute() ;
                writer.append(((epoch<20)?epoch:Double.POSITIVE_INFINITY)+","); //Writes the output into output.csv file.
            }
            writer.append("\n") ;
        }
        writer.close();
    }
}
