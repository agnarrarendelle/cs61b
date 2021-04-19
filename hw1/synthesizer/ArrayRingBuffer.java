package synthesizer;
import java.util.Iterator;

//TODO: Make sure to make this class and all of its methods public
//TODO: Make sure to make this class extend AbstractBoundedQueue<t>
public class ArrayRingBuffer<T> extends AbstractBoundedQueue<T>  {
    /* Index for the next dequeue or peek. */
    private int first;            // index for the next dequeue or peek
    /* Index for the next enqueue. */
    private int last;
    /* Array for storing the buffer data. */
    private T[] rb;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        // TODO: Create new array with capacity elements.
        //       first, last, and fillCount should all be set to 0.
        //       this.capacity should be set appropriately. Note that the local variable
        //       here shadows the field we inherit from AbstractBoundedQueue, so
        //       you'll need to use this.capacity to set the capacity.

        this.first = 0;
        this.last = 0;
        this.fillCount = 0;
        this.capacity = capacity;
        this.rb = (T[]) new Object[capacity];
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow"). Exceptions
     * covered Monday.
     */
    private boolean isAtEnd(int x){
        return x == this.capacity();
    }

    public void enqueue(T x) {
        if(this.isFull()){
            throw new RuntimeException("Ring Buffer Overflow");
        }
        // TODO: Enqueue the item. Don't forget to increase fillCount and update last.
        this.rb[this.last] = x;
        this.last++;
        this.fillCount++;

        if(this.isAtEnd(this.last)){
            this.last = 0;
        }
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow"). Exceptions
     * covered Monday.
     */
    public T dequeue() {
        if(this.isEmpty()){
            throw new RuntimeException("Ring Buffer Underflow");
        }
        // TODO: Dequeue the first item. Don't forget to decrease fillCount and update first
        T returnItem = this.rb[this.first];
        this.first++;
        this.fillCount--;

        if(this.isAtEnd(this.first)){
            this.first = 0;
        }

        return returnItem;
    }

    /**
     * Return oldest item, but don't remove it.
     */
    public T peek() {
        if(this.isEmpty()){
            throw new RuntimeException("Already Empty!!");
        }
        // TODO: Return the first item. None of your instance variables should change.

        return this.rb[this.first];
    }

    public Iterator<T> iterator(){
        return new BufferIterator();
    }

    private class BufferIterator implements Iterator<T>{
        private int wizardPosition;
        private int remaining;

        public BufferIterator(){
            this.wizardPosition = first;
            this.remaining = fillCount;
        }

        @Override
        public boolean hasNext() {
            return remaining > 0;
        }

        public T next(){
          T returnItem = rb[wizardPosition];
          this.wizardPosition++;

          if(this.wizardPosition >= capacity()){
              this.wizardPosition = 0;
          }
          this.remaining--;
          return returnItem;
        }
    }

    // TODO: When you get to part 5, implement the needed code to support iteration.
}
