package furhatos.app.interviewrobot.nlu

import furhatos.nlu.EnumEntity
import furhatos.nlu.Intent
import furhatos.util.Language

// Class that recognizes positive responses
class Good : EnumEntity(stemming = true, speechRecPhrases = true){
    override fun getEnum(lang: Language) : List<String>{
        return listOf("Good", "fine", "great", "ok", "terrific")
    }
}

// Class that recognizes questions about robots state
class HowRU : EnumEntity(stemming = true, speechRecPhrases = true){
    override fun getEnum(lang: Language) : List<String>{
        return listOf("How are you?", "and you?", "what about you?", "how are you doing?")
    }
}

// Class that recognizes negative responses together with question about robots state
class BadAndYou : EnumEntity(stemming = true, speechRecPhrases = true){
    override fun getEnum(lang: Language) : List<String>{
        return listOf("I'm bad, how are you?", "bad, how are you?", "bad, and you?", "bad, what about you?", "bad, how are you doing?",
            "I'm not good, how are you?", "not good, how are you?", "not good, and you?", "not good, what about you?", "not good, how are you doing?",
            "I'm horrible, how are you?", "horrible, how are you?", "horrible, and you?", "horrible, what about you?", "horrible, how are you doing?",
            "I'm horrific, how are you?", "horrific, how are you?", "horrific, and you?", "horrific, what about you?", "horrific, how are you doing?",
            "I'm not great, how are you?", "not great, how are you?", "not great, and you?", "not great, what about you?", "not great, how are you doing?")
    }
}

// Class that recognizes negative responses
class Bad : EnumEntity(stemming = true, speechRecPhrases = true){
    override fun getEnum(lang: Language) : List<String>{
        return listOf("Bad", "horrible", "not good", "not great", "horrific")
    }
}

// Class that recognizes words relating to injury
class Pain : EnumEntity(stemming = true, speechRecPhrases = true){
    override fun getEnum(lang: Language) : List<String>{
        return listOf("Pain", "Hurt", "ache", "sick")
    }
}

// Class that recognizes words related to loneliness
class Lonely : EnumEntity(stemming = true, speechRecPhrases = true){
    override fun getEnum(lang: Language) : List<String>{
        return listOf("Lonely", "Depressed", "isolated", "alone")
    }
}

// Class that recognizes tired or bored
class Tired : EnumEntity(stemming = true, speechRecPhrases = true){
    override fun getEnum(lang: Language) : List<String>{
        return listOf("Tired", "Bored")
    }
}

// Class that recognizes words linked to activities
class Activity : EnumEntity(stemming = true, speechRecPhrases = true) {
    override fun getEnum(lang: Language): List<String> {
        return listOf("Book", "Movie", "Music", "Painting")
    }
}

// Class that recognizes hobbies
class Hobby : EnumEntity(stemming = true, speechRecPhrases = true) {
    override fun getEnum(lang : Language): List<String>{
        return listOf("Swimming pool", "Bingo", "Walking", "Bird watching", "Movies", "Movie theater")
    }
}

// Class with phrases that trigger a list of options
class RequestOptions: Intent() {
    override fun getExamples(lang: Language): List<String> {
        return listOf("What options do you have?", "What are my options?", "What activities do you have?",
            "I don't know", "choices", "what are my choices", "what games do you have?")
    }
}

// Class that recognizes if a user does not want to do any activity
class None : EnumEntity(stemming = true, speechRecPhrases = true){
    override fun getEnum(lang: Language) : List<String>{
        return listOf("None", "Nothing", "No activity", "I don't want to do anything")
    }
}

// Class that contains the available games
class Game : EnumEntity(stemming = true, speechRecPhrases = true){
    override fun getEnum(lang: Language) : List<String>{
        return listOf("Chess", "Bingo", "Card game")
    }
}

// Class that contains some types of exercise
class Exercises : EnumEntity(stemming = true, speechRecPhrases = true){
    override fun getEnum(lang: Language) : List<String>{
        return listOf("Run", "Running", "Walk", "Walking", "Cycling", "Swimming", "Yoga")
    }
}
