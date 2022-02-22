package furhatos.app.interviewrobot.nlu

import furhatos.nlu.EnumEntity
import furhatos.nlu.Intent
import furhatos.nlu.*
import furhatos.util.Language


class Good : EnumEntity(stemming = true, speechRecPhrases = true){
    override fun getEnum(lang: Language) : List<String>{
        return listOf("Good", "fine", "great", "ok")
    }
}

class Bad : EnumEntity(stemming = true, speechRecPhrases = true){
    override fun getEnum(lang: Language) : List<String>{
        return listOf("Bad", "horrible", "not good", "not great")
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
