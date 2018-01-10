# Turing-Machine
Turing Machine Simulator

#Actions allowed:
1) r: move cursor right.
2) l: move cursor left.
3) y: accept input string.
4) n: reject input string.

#States
State Names can be any string, it's preferred to be different from alphabet,for example:
1) state1, state2, ..etc
2) q0, q1, ..etc"

#Alphabets
Language Alphabet can be any string, it's preferred to be only one character,for example:
1)a ,b, c, ..etc
2)1, 2, 3, ..etc

#Transitions
Transition can be written in any of the following formats:
1) q0 0 q1 1 a
2) q0, 0, q1, 1, a
3) (q0, 0) (q1, 1, a)

#Example Programs
The following program will change zeros to ones and vice versa and accepts the string.
q0 1 q0 0 r
q0 0 q0 1 r
q0 # q1 # y

The following program will accepts even number of a's
q0 a q1 a r
q0 b q0 b r
q0 # q1 # n
q1 a q0 a r
q1 b q1 b r
q1 # q1 # y
