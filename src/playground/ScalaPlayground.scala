import spark.sqlContext.implicits._
import org.apache.spark.sql.DataFrame
import org.apache.spark.sql.functions.lit
import org.apache.spark.sql.functions.explode
import org.apache.spark.sql.functions.{from_json, col}

val config = Seq(
  ("""{"key1":"ky1","key3":"ky3"}""")
).toDF("colJson")
val data = Seq(
  (77, "email1", "192", "94")
).toDF("id", "name", "key1", "key3")

data.show()

config.show()
config.printSchema()

val schema = spark.read.json(config.select("colJson").as[String]).schema
val conf = config.select(from_json($"colJson", schema).as("s")).select("s.*")
conf.show(false)

val cols = conf.columns
// cols.foreach(println)

/**
 * cols.map{colName => val newColName = conf.select(col(colName)).first().getString(0)
 * println(colName)
 * println(newColName)
 * val test = data.withColumnRenamed(colName,newColName)
 * test.show()}
 */

def rename(df: DataFrame, colList: Array[String]) = {
  if (colList.size == 0)
    return data
  else {
    val newColName = conf.select(col(colName)).first().getString(0)
    println("colName = " + colList[0])
    println(newColName)
    rename(data.withColumnRenamed(colName, newColName), colList[1:] )
  }
}

rename(data, cols)
data.show(false)

