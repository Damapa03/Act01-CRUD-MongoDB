import com.mongodb.client.MongoClient
import com.mongodb.client.MongoClients
import io.github.cdimascio.dotenv.Dotenv

object JuegosDAO {
    private val dotenv = Dotenv.load()
    // guardo en una variable la url de conexión

    private val urlConnectionMongo =
        "mongodb+srv://dmarin:joIfZQIqNtO99ERy@adacluster.amhxu.mongodb.net/?retryWrites=true&w=majority&appName=ADACluster"

    // podemos realizar la conexion con el cluster
    private val cluster: MongoClient by lazy {
        MongoClients.create(urlConnectionMongo)
    }

    // 2º Nos conectamos a la base de datos
    private val bd by lazy {
        cluster.getDatabase("adaprueba")
    }

    // 3º Con la BD, podemos realizar las consultas a la colección que queramos
    val coll by lazy {
        bd.getCollection("danielmarinpacheco")
    }
}