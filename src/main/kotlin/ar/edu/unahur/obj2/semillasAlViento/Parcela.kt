package ar.edu.unahur.obj2.semillasAlViento

class Parcela(val ancho: Int, val largo: Int, val horasSolPorDia: Int) {
  val plantas = mutableListOf<Planta>()
//  Simplicidad: no se pide saber cuantas plantas hay, ademas se podría hacer plantas.size().
  var cantidadPlantas = 0

  fun superficie() = ancho * largo
  fun cantidadMaximaPlantas() =
//    Redundancia minima: no se hace uso de la función superficie.
    if (ancho > largo) ancho * largo / 5 else ancho * largo / 3 + largo


  fun plantar(planta: Planta) {
//    Robustez: debe arrojar un error, no un print.
    if (cantidadPlantas == this.cantidadMaximaPlantas()) {
      println("Ya no hay lugar en esta parcela")
    } else if (horasSolPorDia > planta.horasDeSolQueTolera() + 2) {
      println("No se puede plantar esto acá, se va a quemar")
    } else {
      plantas.add(planta)
      cantidadPlantas += 1
    }
  }
}

class Agricultora(val parcelas: MutableList<Parcela>) {
//  Mutación controlada: La lista de parcelas no tiene que poder modificarse.
  var ahorrosEnPesos = 20000
//  Simplicidad: el ejercicio no pide un variable así.


  // Suponemos que una parcela vale 5000 pesos
//  Simplicidad: método innecesario para el ejercicio.
  fun comprarParcela(parcela: Parcela) {
    if (ahorrosEnPesos >= 5000) {
      parcelas.add(parcela)
      ahorrosEnPesos -= 5000
    }
  }
//  Cohesion: la parcela tiene que resolver si es semillera o no y con un método poder preguntarle.
//  Esta función tiene demasiadas responsabilidades.
  fun parcelasSemilleras() =
    parcelas.filter {
      parcela -> parcela.plantas.all {
        planta -> planta.daSemillas()
      }
    }


  fun plantarEstrategicamente(planta: Planta) {
    val laElegida = parcelas.maxBy { it.cantidadMaximaPlantas() - it.cantidadPlantas }!!
//  Redundancia minima: hace un add en vez de usar el método existente plantar()
    laElegida.plantas.add(planta)
  }
}
