import com.mongodb.client.FindIterable
import com.mongodb.client.model.Filters
import org.bson.Document
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class JuegoRepository {

    fun create(juego: Juego) {

        try {
            val documento =
                Document().append("titulo", juego.titulo).append("genero", juego.genero).append("precio", juego.precio)
                    .append("fecha-lanzamiento", juego.lanzamiento)

            val duplicado = searchGame(juego.titulo)


            JuegosDAO.coll.insertOne(documento)
        } catch (e: Exception) {
            println("Error al conectar a MongoDB: ${e.message}")
        } finally {
//            JuegosDAO.close()
        }
    }

    fun searchAll(genero: String): MutableList<Document> {
        val lista: MutableList<Document> = mutableListOf()

        try {
            val filtro = Filters.eq("genero", genero)

            val results = JuegosDAO.coll.find(filtro)
            results.forEach { lista.add(it) }
        } catch (e: Exception) {
            println("Error al conectar a MongoDB: ${e.message}")
        } finally {
//            JuegosDAO.close()
        }

        return lista
    }

    fun searchGame(titulo: String): FindIterable<Document?> {
        val filtro = Filters.eq("titulo", titulo)

        return JuegosDAO.coll.find(filtro)
    }

    fun modify(titulo: String, juegoNuevo: Juego) {
        try {
            val juego = comprobarJuego(searchGame(titulo), juegoNuevo)

            val filtro = Filters.eq("titulo", titulo)
            val documentoParaReemplazar = Document()
                .append("titulo", juego.titulo)
                .append("genero", juego.genero)
                .append("precio", juego.precio)
                .append("lanzamiento", juego.lanzamiento)

            JuegosDAO.coll.replaceOne(filtro, documentoParaReemplazar)

        } catch (e: Exception) {
            println("Error al conectar a MongoDB: ${e.message}")
        } finally {
//            JuegosDAO.close()
        }
    }

    fun delete(genero: String) {

        try {
            val filtro = Filters.eq("genero", genero)

            JuegosDAO.coll.deleteMany(filtro)
        } catch (e: Exception) {
            println("Error al conectar a MongoDB: ${e.message}")
        } finally {
//            JuegosDAO.close()
        }
    }

    fun comprobarJuego(searchGame: FindIterable<Document?>, juego: Juego): Juego {
        val dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        var juegoNuevo = juego
        //Comprueba cada parametro de juego y si esta vacio introduce el valor antiguo y en caso contrario introduce el nuevo

        searchGame.forEach { document ->
            document.let {
                juegoNuevo.genero =
                    if (juegoNuevo.genero.isNullOrEmpty()) it?.getString("genero") ?: "" else juegoNuevo.genero
                juegoNuevo.precio = if (juegoNuevo.precio == null) it?.getDouble("precio") ?: 0.0 else juegoNuevo.precio
                juegoNuevo.lanzamiento = if (juegoNuevo.lanzamiento == null) {
                    it?.getString("fechaLanzamiento")?.let { fecha ->
                        LocalDate.parse(fecha, dateFormatter) // Convertir String a LocalDate
                    }
                } else {
                    juego.lanzamiento
                }
            }
        }

        return juegoNuevo

    }

}