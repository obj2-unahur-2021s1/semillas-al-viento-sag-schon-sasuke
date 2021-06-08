package ar.edu.unahur.obj2.semillasAlViento

abstract class Planta(val anioObtencionSemilla: Int, var altura: Float) {
//  Simplicidad: la altura no debería poder cambiarse, el enunciado lo aclara.
  fun esFuerte() = this.horasDeSolQueTolera() > 10

//  (Des)acoplamiento: Este método debería pertenecer a la clase "Parcela".
  fun parcelaTieneComplicaciones(parcela: Parcela) =
    parcela.plantas.any { it.horasDeSolQueTolera() < parcela.horasSolPorDia }

  abstract fun horasDeSolQueTolera(): Int

  abstract fun daSemillas(): Boolean
}

class Menta(anioObtencionSemilla: Int, altura: Float) : Planta(anioObtencionSemilla, altura) {
  override fun horasDeSolQueTolera() = 6

  override fun daSemillas() = this.esFuerte() || altura > 0.4
}

open class Soja(anioObtencionSemilla: Int, altura: Float) : Planta(anioObtencionSemilla, altura) {
  override fun horasDeSolQueTolera(): Int {
    return when {
      altura < 0.5 -> 6
      altura < 1 -> 7
      else -> 9
    }
  }


  override fun daSemillas(): Boolean  {
//  Acá el mismo problema que arriba.
    if (this.esTransgenica) {
      return false
    }

    return this.esFuerte() || (this.anioObtencionSemilla > 2007 && this.altura > 1)
  }
}
