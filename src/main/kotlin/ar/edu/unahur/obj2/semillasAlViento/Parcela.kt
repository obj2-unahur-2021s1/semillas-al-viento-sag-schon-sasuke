package ar.edu.unahur.obj2.semillasAlViento

class Parcela(private val ancho: Int, private val largo: Int, private val horasSolPorDia: Int) {
  val plantas = mutableListOf<Planta>()

  fun tieneComplicaciones() = this.plantas.any { it.horasDeSolQueTolera() < this.horasSolPorDia }

  fun superficie() = ancho * largo

  fun cantidadMaximaPlantas() =
    if (ancho > largo) this.superficie() / 5 else this.superficie() / 3 + largo


  fun plantar(planta: Planta) {
    when {
        this.plantas.size == this.cantidadMaximaPlantas() -> {
          throw error("Ya no hay lugar en esta parcela")
        }
        this.horasSolPorDia > planta.horasDeSolQueTolera() + 2 -> {
          throw error("No se puede plantar esto acá, se va a quemar")
        }
        else -> {
          plantas.add(planta)
        }
    }
  }

  fun esSemillera() = plantas.all { it.daSemillas() }

}

class Agricultora(val parcelas: List<Parcela>) {

  fun parcelasSemilleras() = parcelas.all { parcela -> parcela.esSemillera() }

  fun plantarEstrategicamente(planta: Planta) {
    val laElegida = parcelas.maxByOrNull { it.cantidadMaximaPlantas() - it.plantas.size }!!
    laElegida.plantar(planta)
  }
}
