import com.mongodb.client.MongoClient
import com.mongodb.client.MongoClients
import io.github.cdimascio.dotenv.dotenv

object JuegosDAO {
    val dotenv = dotenv()
    // guardo en una variable la url de conexión
    val urlConnectionMongo = dotenv["URL_MONGODB"]
    // podemos realizar la conexion con el cluster
    val cluster: MongoClient = MongoClients.create(urlConnectionMongo)

    // 2º Nos conectamos a la base de datos
    val bd = cluster.getDatabase("adaprueba")

    // 3º Con la BD, podemos realizar las consultas a la colección que queramos
    val coll = bd.getCollection("danielmarinpacheco")

}