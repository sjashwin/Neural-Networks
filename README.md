# Neural Networks
This is a simple perceptron using *Hebbian learning*. This perceptron is a simulation of an AND gate.

X1 | X2 | b | Y  |
---|----|---|----|
1  | 1  | 1 | 1  |
1  | -1 | 1 | -1 |
-1 | 1  | 1 | -1 |
-1 | -1 | 1 | -1 |
For more information on hebbs network :
https://en.wikibooks.org/wiki/Artificial_Neural_Networks/Hebbian_Learning

It takes 10 epochs to converge at learning rate set to 1.0.
Finding new weights:
```java
w1 = ( w1+( alpha*target[i]*inputs[i][0] ) ) ;
w2 = ( w2+( alpha*target[i]*inputs[i][1] ) ) ;
```

#File Output
An output.csv file is produced on execution of the program. It falicitates in analysis of the perceptron.Learning rate from 1.0 - 10.0 and threshold is from 0.1 - 0.5. 
