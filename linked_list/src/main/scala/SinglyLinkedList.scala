class Node[A](var data: A, var next: Node[A]) {
  // This is the object representing an item in our linked list.
  // It contains the data of the list item and a link to the next item in the list.
  // An auxiliary constructor is used to just take data.
  def this(x: A) {
    this(x, null)
  }
}

class SinglyLinkedList[A](var head: Node[A]) {

  // Auxiliary constructor for creating a linked list with multiple initial values.
  def this(xs: Array[A]) {
    this(null)
    for(x <- xs)
      this.append(x)
  }

  /**
   * Method for adding an item to the linked list. It looks for the end of the list
   * and adds the new node to it.
   *
   * @param x = The element to be added to the end of the list.
   */
  def append(x: A): Unit = {
    if(this.isEmpty)
      head.data = x
    else {
      var working_node = head
      while (working_node.next != null) {
        working_node = working_node.next
      }
      working_node.next = new Node[A](x)
    }
  }

  /**
   * Helper method to add many new items at the same time.
   *
   * @param xs = An array of elements to be add sequentially at the end of the list.
   */
  def appendMany(xs: Array[A]): Unit = {
    for(x <- xs)
      this.append(x)
  }

  /**
   * This method prepends an item to the list (adds it to the front).
   *
   * @param x = The element to be prepended to the list.
   */
  def prepend(x: A): Unit = {
    val newHead = new Node[A](x, head)
    this.head = newHead
  }

  /**
   * This method prepends items to the list in the order given (the last item is prepended,
   * then the second to last, and so on) so that the items maintain their order on the list.
   *
   * @param xs = Array of elements to prepend to the list.
   */
  def prependMany(xs: Array[A]): Unit = {
    val len = xs.length
    for(i <- 1 to len)
      this.prepend(xs(len-i))
  }

  /**
   * This method removes an item from the linked list by searching the list for it, then
   * bridging the previous and next items in the list, to remove the item from the chain.
   *
   * @param x = The element to be sought and removed from the list.
   */
  def remove(x: A): Unit = {
    var working_node = head
    var prev_node: Node[A] = null
    while(working_node.data != x || working_node.next != null) {
      prev_node = working_node
      working_node = working_node.next
    }
    if(working_node.next == null && working_node.data != x)
      println(s"ERROR: Could not find \"$x\" in the list.")
    else
      prev_node.next = working_node.next
  }

  /**
   * Helper method to remove many items from the list at once.
   *
   * @param xs = Array of elements to remove from the list, sequentially.
   */
  def removeMany(xs: Array[A]): Unit = {
    for(x <- xs)
      this.remove(x)
  }

  /**
   * This method inserts an item into the list after a given item value. If the insert value is not
   * found, the method will fail and output a notification to the console.
   *
   * @param x   = Item to be inserted into the list
   * @param inX = Item that the new item will be inserted after.
   */
  def insert(x: A, inX: A): Unit = {
    var working_node = head
    var prev_node: Node[A] = null
    while(working_node.data != inX || working_node.next != null) {
      prev_node = working_node
      working_node = working_node.next
    }
    if(working_node.next == null && working_node.data != inX)
      println(s"ERROR: Could not find \"$inX\" in the list.")
    else {
      prev_node.next = new Node[A](x, working_node.next)
    }
  }

  /**
   * Helper function to insert many items in a row at on position.
   *
   * @param xs    = An array of elements to be inserted into the list sequentially.
   * @param inX   = The element after which the new items should be inserted.
   */
  def insertMany(xs: Array[A], inX: A): Unit = {
    var in = inX
    for(x <- xs) {
      this.insert(x, in)
      in = x
    }
  }

  /**
   * This method searches a list for an element.
   *
   * @param x   = The item being searched for
   * @return Boolean value for whether the item was found or not.
   */
  def contains(x: A): Boolean = {
    var bool = false
    var working_node = head

    while(working_node != null) {
      if (working_node.data == x)
        bool = true
      working_node = working_node.next
    }

    bool
  }

  /**
   * This method determines if a list is empty or not.
   *
   * @return The boolean value of whether or not the list is empty.
   */
  def isEmpty: Boolean = {
    var bool = false
    if (head.data == null && head.next == null)
      bool = true
    bool
  }

  /**
   * This method returns the number of items in the list.
   *
   * @return An integer representing the number of items in the list.
   */
  def length(): Int = {
    var counter = 1
    var working_node = head

    while(working_node.next != null) {
      counter = counter + 1
      working_node = working_node.next
    }

    if(this.isEmpty)
      counter = 0

    counter
  }
}