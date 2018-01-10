# Turing-Machine
Turing Machine Simulator

## Actions allowed:
- r: move cursor right.
- l: move cursor left.
- y: accept input string.
- n: reject input string.

## States
State Names can be any string, it's preferred to be different from alphabet,for example:
- state1, state2, ..etc
- q0, q1, ..etc"

## Alphabets
Language Alphabet can be any string, it's preferred to be only one character,for example:
- a ,b, c, ..etc
- 1, 2, 3, ..etc

## Transitions
Transition can be written in any of the following formats:
- q0 0 q1 1 a
- q0, 0, q1, 1, a
- (q0, 0) (q1, 1, a)

## Example Programs
The following program will change zeros to ones and vice versa and accepts the string.
<br>
q0 1 q0 0 r
<br>
q0 0 q0 1 r
<br>
q0 # q1 # y
<br>

The following program will accepts even number of a's
<br>
q0 a q1 a r
<br>
q0 b q0 b r
<br>
q0 # q1 # n
<br>
q1 a q0 a r
<br>
q1 b q1 b r
<br>
q1 # q1 # y
