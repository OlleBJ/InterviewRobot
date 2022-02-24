package furhatos.app.interviewrobot.nlu

import furhatos.nlu.EnumEntity
import furhatos.nlu.Intent
import furhatos.nlu.*
import furhatos.util.Language


class Good : EnumEntity(stemming = true, speechRecPhrases = true){
    override fun getEnum(lang: Language) : List<String>{
        return listOf("Good", "fine", "great", "ok", "terrific")
    }
}

class Bad : EnumEntity(stemming = true, speechRecPhrases = true){
    override fun getEnum(lang: Language) : List<String>{
        return listOf("Bad", "horrible", "not good", "not great", "horrific")
    }
}

class Pain : EnumEntity(stemming = true, speechRecPhrases = true){
    override fun getEnum(lang: Language) : List<String>{
        return listOf("Pain", "Hurt", "ache", "sick")
    }
}

class Lonely : EnumEntity(stemming = true, speechRecPhrases = true){
    override fun getEnum(lang: Language) : List<String>{
        return listOf("Lonely", "Depressed", "isolated", "alone")
    }
}

class Tired : EnumEntity(stemming = true, speechRecPhrases = true){
    override fun getEnum(lang: Language) : List<String>{
        return listOf("Tired", "Bored")
    }
}

class Activity : EnumEntity(stemming = true, speechRecPhrases = true) {
    override fun getEnum(lang: Language): List<String> {
        return listOf("Book", "Movie", "Music", "Painting")
    }
}

class Hobby : EnumEntity(stemming = true, speechRecPhrases = true) {
    override fun getEnum(lang : Language): List<String>{
        return listOf("Swimming pool", "Bingo", "Walking", "Bird watching", "Movies", "Movie theater")
    }
}

class RequestOptions: Intent() {
    override fun getExamples(lang: Language): List<String> {
        return listOf("What options do you have?", "What are my options?", "What activities do you have?", "I don't know")
    }
}

class None : EnumEntity(stemming = true, speechRecPhrases = true){
    override fun getEnum(lang: Language) : List<String>{
        return listOf("None", "Nothing", "No activity", "I don't want to do anything")
    }
}

class Game : EnumEntity(stemming = true, speechRecPhrases = true){
    override fun getEnum(lang: Language) : List<String>{
        return listOf("Chess", "Bingo", "Card game")
    }
}

class Exercises : EnumEntity(stemming = true, speechRecPhrases = true){
    override fun getEnum(lang: Language) : List<String>{
        return listOf("Run", "Running", "Walk", "Walking", "Cycling", "Swimming", "Yoga")
    }
}
