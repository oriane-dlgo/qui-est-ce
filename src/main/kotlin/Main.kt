import info.but1.sae2025.QuiEstCeClient
import kotlin.random.Random

fun main() {
    println("Hello, World!")
    var client: QuiEstCeClient
    client = QuiEstCeClient("172.26.69.145", 8080)
    // configuration à modifier bien entendu
    client.requeteEssai()
}