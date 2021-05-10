package ar.edu.unahur.obj2.semillasAlViento

abstract class Planta(val anioObtencionSemilla: Int, var altura: Float) {
  fun esFuerte() = this.horasDeSolQueTolera() > 10

  //Desacoplamiento:
  /*Si hay acoplamiento entre los objetos puede aumentar los errores y cambios.*/
  /*Esta tomando una resposabilidad que tendria que hacer la parcela.*/
  /*Este metodo segun el enunciado tendria que ir en la clase parcela.*/
  fun parcelaTieneComplicaciones(parcela: Parcela) =
    parcela.plantas.any { it.horasDeSolQueTolera() < parcela.horasSolPorDia }

  abstract fun horasDeSolQueTolera(): Int
  abstract fun daSemillas(): Boolean
}

class Menta(anioObtencionSemilla: Int, altura: Float) : Planta(anioObtencionSemilla, altura) {
  override fun horasDeSolQueTolera() = 6
  override fun daSemillas() = this.esFuerte() || altura > 0.4
}

class Soja(anioObtencionSemilla: Int, altura: Float, val esTransgenica: Boolean) : Planta(anioObtencionSemilla, altura) {

  //Mutaciones Controladas:
  /*Se hizo una variable "altura" que es mutable, pero en ningun comportamiento del objt veo que cambie.*/
  /*Esto podria ocasionar problemas, con datos, si se cambia la altura por error, podria variar la "horaBase", esto puede traer problemas.*/
  /*Tambien no veo que sea necesaria. A la hora de hacer mutabilidad y no usarla*/
  override fun horasDeSolQueTolera(): Int  {
    // ¡Magia de Kotlin! El `when` es como un `if` pero más poderoso:
    // evalúa cada línea en orden y devuelve lo que está después de la flecha.
    val horasBase = when {
      altura < 0.5  -> 6
      altura < 1    -> 7
      else          -> 9
    }

    return if (esTransgenica) horasBase * 2 else horasBase
  }


  override fun daSemillas(): Boolean  {
    if (this.esTransgenica) {
      return false
    }

    return this.esFuerte() || (this.anioObtencionSemilla > 2007 && this.altura > 1)
  }
}
