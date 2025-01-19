import java.time.LocalDate

fun main() {
    val juegoRepository: JuegoRepository = JuegoRepository()
    var num = 0

    while (true) {
        println("Menu: \n\r1. Listar juegos \n\r2. Registrar juegos \n\r3. Modificar juego \n\r4. Eliminar juego \n\r5. Salir")
        try {
            num = readLine()!!.toInt()
        } catch (e: NumberFormatException) {
            println("Por favor introduzca un numero")
        }

        if (num == 1) {
            println("Itroduzca el genero de los juegos que quiere")
            val genero = readln()
            println(juegoRepository.searchAll(genero))
        }
        if (num == 2) {
            println("Introduzca el titulo")
            val titulo = readln()
            if (titulo != "") {
                println("Introduzca el genero")
                val genero = readln()
                println("Introduzca el precio")
                val precio = readln().toDouble()
                println("Introduzca la fecha de lanzamiento")
                val fecha = LocalDate.now()
                val juego = Juego(titulo, genero, precio, fecha)

                juegoRepository.create(juego)
            } else println("El titulo del juego es obligatorio")


        }
        if (num == 3) {

        }
        if (num == 4) {
            println("Introduzca el genero de los juegos que quiere borrar")
            val genero = readln()
            juegoRepository.delete(genero)
        }
        if (num == 5) {
            break
        }
        if (num < 0 || num > 5) {
            println("Introduzca un numero valido")
        }
    }
}