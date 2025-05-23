package Sesion07a.bstreeInterface;

import Sesion07a.Exceptions.*;

public interface BinarySearchTree<E> {
    void insert(E data) throws ItemDuplicated;
    E search(E data) throws ItemNoFound;
    void delete(E data) throws ExceptionIsEmptyBST;
    boolean isEmpty();
}
