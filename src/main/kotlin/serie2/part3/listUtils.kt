package serie2.part3

class Node<T> (
    var value: T = Any() as T,
    var next: Node<T>? = null,
    var previous: Node<T>? = null) {
}

fun splitEvensAndOdds(list:Node<Int>){
    val pares = Node<Int>()
    pares.next = pares
    pares.previous = pares

    val impares = Node<Int>()
    impares.next = impares
    impares.previous = impares

    val cmp = Comparator<Int> { x, y -> x - y }

    var aux = list.next
    while (aux != list) {
        val valor = aux!!.value
        aux = aux.next

        remove(list, valor, cmp)

        if (valor % 2 == 0) {
            add(pares, valor)
        } else {
            add(impares, valor)
        }
    }
    var p = pares.previous
    while (p != pares) {
        add(list, p!!.value)
        p = p.previous
    }
    p = impares.previous
    while (p != impares) {
        add(list, p!!.value)
        p = p.previous
    }
}


fun <T> intersection(list1: Node<T>, list2: Node<T>, cmp: Comparator<T>): Node<T>? {
    var no1 = list1.next
    var no2 = list2.next

    var head: Node<T>? = null
    var tail: Node<T>? = null

    while (no1 != list1 && no2 != list2) {
        val comp = cmp.compare(no1!!.value, no2!!.value)

        when {
            comp < 0 -> no1 = no1.next
            comp > 0 -> no2 = no2.next
            else -> {
                val prox1 = no1.next
                val prox2 = no2.next

                if (tail == null || cmp.compare(tail.value, no1.value) != 0) {
                    val newNode = no1

                    newNode.previous?.next = newNode.next
                    newNode.next?.previous = newNode.previous

                    no2!!.previous?.next = no2.next
                    no2.next?.previous = no2.previous

                    newNode.previous = tail
                    newNode.next = null

                    if (tail != null) {
                        tail.next = newNode
                    } else {
                        head = newNode
                    }
                    tail = newNode
                }
                no1 = prox1
                no2 = prox2
            }
        }
    }
    return head
}

// insere um novo elemento na primeira posição da lista
fun <T> add(head: Node<T>, newItem: T) {
    val x = Node(newItem)
    x.next = head.next
    head.next?.previous = x
    head.next = x
    x.previous = head
}

// remove o elemento da lista com chave k
fun <T> remove(head: Node<T>, k: T, cmp:Comparator<T>) {
    var x = head.next
    while (x != head) {
        if (x != null) {
            if (cmp.compare(k, x.value) == 0) {
                if (x != null) {
                    x.previous?.next = x.next
                }
                if (x != null) {
                    x.next?.previous = x.previous
                }
                break
            }
            else x = x.next
        }
    }
}