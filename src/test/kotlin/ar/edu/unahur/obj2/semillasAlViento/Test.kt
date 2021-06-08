package ar.edu.unahur.obj2.semillasAlViento

import io.kotest.assertions.throwables.shouldThrowAny
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.shouldBe

class  Test : DescribeSpec ({
    val plantaDeMenta = Menta(2018,0.8F)
    val plantaDeSoja = Soja(2019,0.9F)
    val plantaSojaTransgenica = SojaTransgenica(1996,1.5F)

    describe("Requerimiento 1"){

        it("horas que tolera"){
            plantaDeMenta.horasDeSolQueTolera().shouldBe(6)
            plantaDeSoja.horasDeSolQueTolera().shouldBe(7)
        }

        it("es fuerte"){
            plantaDeMenta.esFuerte().shouldBeFalse()
            plantaDeSoja.esFuerte().shouldBeFalse()
        }

        it("da semillas"){
            plantaDeMenta.daSemillas().shouldBeTrue()
            plantaDeSoja.daSemillas().shouldBeFalse()
        }

    }

    describe("Requerimiento 2"){

        it("Da semillas"){
            plantaSojaTransgenica.daSemillas().shouldBe(false)
        }

        it("Horas de sol"){
            plantaSojaTransgenica.horasDeSolQueTolera().shouldBe(18F)
        }

    }

    describe("Requerimiento 3"){
        val parcela2 = Parcela(20,1,8)

        parcela2.superficie().shouldBe(20)
        parcela2.cantidadMaximaPlantas().shouldBe(4)

        val plantaDeSoja1 = Soja(2017,1F,false)
        val plantaDeSoja2 = Soja(2017,1F,false)
        val plantaDeSoja3 = Soja(2017,1F,false)
        val plantaDeSoja4 = Soja(2017,1F,false)

        parcela2.plantar(plantaDeSoja1)
        parcela2.plantar(plantaDeSoja2)
        parcela2.plantar(plantaDeSoja3)
        parcela2.plantar(plantaDeSoja4)
        parcela2.tieneComplicaciones().shouldBeFalse()
        parcela2.plantas.size.shouldBe(4)
        parcela2.cantidadMaximaPlantas().shouldBe(4)

        val plantaDeSoja5 = Soja(2017,1F)
        shouldThrowAny{
            parcela2.plantar(plantaDeSoja5)
        }

    }

    describe("Requerimiento 4"){
        val parcela2 = Parcela(150,300,1)
        val parcela3 = Parcela(150,300,1)

        parcela2.plantar(plantaDeMenta)
        parcela3.plantar(plantaDeMenta)

        val agricultor = Agricultora(mutableListOf<Parcela>(parcela2,parcela3))

        it("Parcelas semilleras"){
            agricultor.parcelasSemilleras().shouldBe(true)
        }

        it("Poder plantar en la parcela que mas lugar tenga") {
            parcela3.plantar(plantaDeSoja)
            parcela3.plantar(plantaSojaTransgenica)

            parcela2.plantas.size.shouldBe(1)

            agricultor.plantarEstrategicamente(plantaDeSoja)

            parcela2.plantas.size.shouldBe(2)
        }
    }

})