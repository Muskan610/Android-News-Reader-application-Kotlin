package nl.bhat.muskan.newsreaderstudent636130.GetResults

import com.google.gson.annotations.SerializedName

data class ResultList(@SerializedName("Results") var Results: ArrayList<ResultsDTO>, var NextId: Int) {
}