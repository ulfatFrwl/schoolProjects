
wordrange: main.o avl.o
	g++ main.o avl.o -o wordrange
	
main.o: main.cpp
	g++ -c main.cpp
	
avl.o: avl.cpp avl.h
	g++ -c avl.cpp
	
clean:
	rm *.o wordrange