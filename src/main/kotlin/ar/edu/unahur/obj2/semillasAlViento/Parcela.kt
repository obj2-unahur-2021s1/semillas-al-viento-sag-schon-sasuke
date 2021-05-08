package ar.edu.unahur.obj2.semillasAlViento

class Parcela(val ancho: Int, val largo: Int, val horasSolPorDia: Int) {
  val plantas = mutableListOf<Planta>()
  var cantidadPlantas = 0

  fun superficie() = ancho * largo

  //Flexibilidad:
  /*Si se ve con el paradigma del eje extensibilidad. Se prodria utilizar un metodo ya existenten para este comportamtiento.*/
  /*Seria el metodo "superficie()".*/
  /*Este metodo se podria utilizar en "cantidadMaximaPlantas()".*/
  //Cohesion:
  /*Error de cohesion.*/
  /*La clase internamente no se esta utilizando ella misma y por ende aumenta mas las responsabilidades de dicha clase.*/
  /*Siendo menos cohesiva*/
  //Redundacia minima:
  /*Otro error encontrado, es codigo  redundante.*/
  /*Es mas propenso a errores y mas dificil de ubicarlo, si se sigue este patron.*/
  fun cantidadMaximaPlantas() =
    if (ancho > largo) ancho * largo / 5 else ancho * largo / 3 + largo


  //En este requerimiento se pide que de un error no un print.
  fun plantar(planta: Planta) {
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
  var ahorrosEnPesos = 20000

  //En este caso, yo le añadiria a la clase parcela un atributo o metodo que por el ancho y largo, calcule un valor en pesos.
  //Cada parcela va a tener dimensiones distintas con valores en pesos distintos. Cada agricultora podria tener mas o menos parcelas y no la misma cantidad.
  // Atte pepe. PD: Agrege este comentario porque me parecio mejor a la hora de comprar parcelas.
  // No se si esto entra o no, a la hora de evaluar el tp. :D

  // Suponemos que una parcela vale 5000 pesos
  fun comprarParcela(parcela: Parcela) {
    if (ahorrosEnPesos >= 5000) {
      parcelas.add(parcela)
      ahorrosEnPesos -= 5000
    }
  }

  //Redundacia minima:
  /*El codigo es  repetitivo. Quedaria mejor si solo utiliamos el "it".*/
  fun parcelasSemilleras() =
    parcelas.filter {
      parcela -> parcela.plantas.all {
        planta -> planta.daSemillas()
      }
    }

  fun plantarEstrategicamente(planta: Planta) {
    val laElegida = parcelas.maxBy { it.cantidadMaximaPlantas() - it.cantidadPlantas }!!
    laElegida.plantas.add(planta)
  }
}
