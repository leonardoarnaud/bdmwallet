package br.eti.arnaud.bdmwallet.app.remote.model.response

data class TransactionsResponse (
    val senderPublicKey: String,
    val amount: Long,
    val signature: String,
    val fee: Int,
    val type: Int,
    val version: Int,
    val attachment: String,
    val sender: String,
    val feeAssetId: String?,
    val proofs: List<String>,
    val assetId: String?,
    val recipient: String,
    val feeAsset: String?,
    val id: String,
    val timestamp: Long,
    val height: Int
){
    fun imSending(myWalletAddress: String): Boolean {
        return sender == myWalletAddress
    }
}