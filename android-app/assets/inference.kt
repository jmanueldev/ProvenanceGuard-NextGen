class MalwareModel(context: Context) {



    private val interpreter: Interpreter



    init {

        val model = context.assets.open("malware_model.tflite").readBytes()

        interpreter = Interpreter(model)

    }



    fun predict(f: Features): Float {



        val input = floatArrayOf(

            f.netAfterContacts.toFloat(),

            if (f.execToNet) 1f else 0f,

            if (f.smsAfterNet) 1f else 0f,

            f.uniqueDomains.toFloat(),

            f.graphDensity

        )



        val output = Array(1) { FloatArray(1) }



        interpreter.run(arrayOf(input), output)



        return output[0][0]

    }

}