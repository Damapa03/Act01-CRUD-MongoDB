import com.mongodb.client.model.Filters
import org.bson.Document

class JuegoRepository {

    fun create(juego: Juego) {

        val documento = Document().append("titulo", juego.titulo).append("genero", juego.genero).append("precio", juego.precio).append("fecha-lanzamiento", juego.lanzamiento)

        JuegosDAO.coll.insertOne(documento)

    }
    fun searchAll(genero: String): MutableList<Document> {
        val lista: MutableList<Document> = mutableListOf()

        val filtro = Filters.eq("genero", genero)

        val results = JuegosDAO.coll.find(filtro)
        results.forEach { lista.add(it) }
        return lista
    }

    fun searchGame(titulo: String){
        val filtro = Filters.eq("titulo", titulo)

        JuegosDAO.coll.find()

    }

    fun modify(juego: Juego, juegoNuevo: Juego){
        try {

        }catch (e: Exception){
            JuegosDAO.close()
        }
    }
    fun delete(genero: String){

        val filtro = Filters.eq("genero", genero)

        JuegosDAO.coll.deleteMany(filtro)
    }
}