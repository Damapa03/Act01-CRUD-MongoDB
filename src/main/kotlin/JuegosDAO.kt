import com.mongodb.client.MongoClient
import com.mongodb.client.MongoClients
import io.github.cdimascio.dotenv.dotenv

object JuegosDAO {
//    private val dotenv = dotenv()
    // guardo en una variable la url de conexión
    private val urlConnectionMongo = "mongodb+srv://dmarin:joIfZQIqNtO99ERy@adacluster.amhxu.mongodb.net/?retryWrites=true&w=majority&appName=ADACluster"
    // podemos realizar la conexion con el cluster
    private val cluster: MongoClient = MongoClients.create(urlConnectionMongo)

    // 2º Nos conectamos a la base de datos
    private val bd = cluster.getDatabase("adaprueba")

    // 3º Con la BD, podemos realizar las consultas a la colección que queramos
    val coll = bd.getCollection("danielmarinpacheco")

    fun close (){
        cluster.close()
    }
}